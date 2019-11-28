package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.UserReportRepository;
import com.demo.practical_training.entity.UserReport;
import com.demo.practical_training.manage.service.UserReportService;
import com.demo.practical_training.model.request.QueryUserReportRequest;
import com.demo.practical_training.model.response.UserReportResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional()
public class UserReportServiceImpl implements UserReportService {
    @Autowired
    UserReportRepository UserReportRepository;
    /**
     * 分页和排序加动态查询用户举报页面
     *
     * @param pageRequest
     * @param queryUserReportRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserReportRequest queryUserReportRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        //模糊匹配别名
//        exampleMatcher = exampleMatcher.withMatcher("UserReportTitle", ExampleMatcher.GenericPropertyMatchers.contains());
//        //创建条件值对象
        UserReport UserReport = new UserReport();
//        //判断用户举报标题是否为空
//        if (StringUtils.isNotEmpty(queryUserReportRequest.getUserReportName())) {
//            UserReport.setUserReportName(queryUserReportRequest.getUserReportName());
//        }
        //判断用户举报id是否为空
        if (StringUtils.isNotEmpty(queryUserReportRequest.getId())) {
            UserReport.setId(queryUserReportRequest.getId());
        }
        //创建条件实例对象
        Example<UserReport> example = Example.of(UserReport, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<UserReport> all = UserReportRepository.findAll(example, pageRequest.getPageable());
        //新建QueryResult<T> 对象
        QueryResult<UserReport> UserReportQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        UserReportQueryResult.setList(all.getContent());
        UserReportQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, UserReportQueryResult);
    }

    /**
     * 新增用户举报
     * @param UserReport
     * @return
     */
    @Override
    public UserReportResult add(UserReport UserReport) {
        UserReport UserReport1 = UserReportRepository.save(UserReport);
        return new UserReportResult(CommonCode.SUCCESS,UserReport1);
    }

    /**
     * 根据id修改用户举报
     * @param id
     * @param UserReport
     * @return
     */
    @Override
    public UserReportResult updateById(String id, UserReport UserReport) {
        //根据Id查询用户举报
        UserReport UserReport1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(UserReport1!=null){
            
            UserReportRepository.save(UserReport1);
            UserReport UserReport2 = UserReportRepository.save(UserReport1);
            if(UserReport2!=null){
                return new UserReportResult(CommonCode.SUCCESS,UserReport2);
            }

        }
        //若不存在，则返回失败
        return new UserReportResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除用户举报
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询用户举报
        UserReport UserReport1 = this.findById(id);
        if(UserReport1!=null){
            //若存在，删除用户举报
            UserReportRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询用户举报
     * @param id
     * @return
     */
    @Override
    public UserReport findById(String id) {
        Optional<UserReport> optional = UserReportRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
