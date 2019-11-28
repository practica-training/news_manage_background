package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.NewsManagementLogRepository;
import com.demo.practical_training.entity.NewsManagementLog;
import com.demo.practical_training.manage.service.NewsManagementLogService;
import com.demo.practical_training.model.request.QueryNewsManagementLogRequest;
import com.demo.practical_training.model.response.NewsManagementLogResult;
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
public class NewsManagementLogServiceImpl implements NewsManagementLogService {
    @Autowired
    NewsManagementLogRepository NewsManagementLogRepository;
    /**
     * 分页和排序加动态查询管理员的新闻管理日志页面
     *
     * @param pageRequest
     * @param queryNewsManagementLogRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsManagementLogRequest queryNewsManagementLogRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        //模糊匹配别名
//        exampleMatcher = exampleMatcher.withMatcher("NewsManagementLogTitle", ExampleMatcher.GenericPropertyMatchers.contains());
//        //创建条件值对象
        NewsManagementLog NewsManagementLog = new NewsManagementLog();
//        //判断管理员的新闻管理日志标题是否为空
//        if (StringUtils.isNotEmpty(queryNewsManagementLogRequest.getNewsManagementLogName())) {
//            NewsManagementLog.setNewsManagementLogName(queryNewsManagementLogRequest.getNewsManagementLogName());
//        }
        //判断管理员的新闻管理日志id是否为空
        if (StringUtils.isNotEmpty(queryNewsManagementLogRequest.getId())) {
            NewsManagementLog.setId(queryNewsManagementLogRequest.getId());
        }
        //创建条件实例对象
        Example<NewsManagementLog> example = Example.of(NewsManagementLog, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<NewsManagementLog> all = NewsManagementLogRepository.findAll(example, pageRequest.getPageable());
        //新建QueryResult<T> 对象
        QueryResult<NewsManagementLog> NewsManagementLogQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        NewsManagementLogQueryResult.setList(all.getContent());
        NewsManagementLogQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, NewsManagementLogQueryResult);
    }

    /**
     * 新增管理员的新闻管理日志
     * @param NewsManagementLog
     * @return
     */
    @Override
    public NewsManagementLogResult add(NewsManagementLog NewsManagementLog) {
        NewsManagementLog NewsManagementLog1 = NewsManagementLogRepository.save(NewsManagementLog);
        return new NewsManagementLogResult(CommonCode.SUCCESS,NewsManagementLog1);
    }

    /**
     * 根据id修改管理员的新闻管理日志
     * @param id
     * @param NewsManagementLog
     * @return
     */
    @Override
    public NewsManagementLogResult updateById(String id, NewsManagementLog NewsManagementLog) {
        //根据Id查询管理员的新闻管理日志
        NewsManagementLog NewsManagementLog1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(NewsManagementLog1!=null){
            
            NewsManagementLogRepository.save(NewsManagementLog1);
            NewsManagementLog NewsManagementLog2 = NewsManagementLogRepository.save(NewsManagementLog1);
            if(NewsManagementLog2!=null){
                return new NewsManagementLogResult(CommonCode.SUCCESS,NewsManagementLog2);
            }

        }
        //若不存在，则返回失败
        return new NewsManagementLogResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除管理员的新闻管理日志
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询管理员的新闻管理日志
        NewsManagementLog NewsManagementLog1 = this.findById(id);
        if(NewsManagementLog1!=null){
            //若存在，删除管理员的新闻管理日志
            NewsManagementLogRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询管理员的新闻管理日志
     * @param id
     * @return
     */
    @Override
    public NewsManagementLog findById(String id) {
        Optional<NewsManagementLog> optional = NewsManagementLogRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
