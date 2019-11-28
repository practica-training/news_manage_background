package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.UserViolationRepository;
import com.demo.practical_training.entity.UserViolation;
import com.demo.practical_training.manage.service.UserService;
import com.demo.practical_training.manage.service.UserViolationService;
import com.demo.practical_training.model.request.QueryUserViolationRequest;
import com.demo.practical_training.model.response.UserViolationResult;
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
public class UserViolationServiceImpl implements UserViolationService {
    @Autowired
    UserViolationRepository UserViolationRepository;
    @Autowired
    UserService UserService;
    /**
     * 分页和排序加动态查询用户违规页面
     *
     * @param pageRequest
     * @param queryUserViolationRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserViolationRequest queryUserViolationRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        //模糊匹配别名
//        exampleMatcher = exampleMatcher.withMatcher("UserViolationTitle", ExampleMatcher.GenericPropertyMatchers.contains());
//        //创建条件值对象
        UserViolation UserViolation = new UserViolation();
//        //判断用户违规标题是否为空
//        if (StringUtils.isNotEmpty(queryUserViolationRequest.getUserViolationName())) {
//            UserViolation.setUserViolationName(queryUserViolationRequest.getUserViolationName());
//        }
        //判断用户违规id是否为空
        if (StringUtils.isNotEmpty(queryUserViolationRequest.getViolationID())) {
            UserViolation.setId(queryUserViolationRequest.getViolationID());
        }
        //创建条件实例对象
        Example<UserViolation> example = Example.of(UserViolation, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<UserViolation> all = UserViolationRepository.findAll(example, pageRequest.getPageable());
        //新建QueryResult<T> 对象
        QueryResult<UserViolation> UserViolationQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        UserViolationQueryResult.setList(all.getContent());
        UserViolationQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, UserViolationQueryResult);
    }

    /**
     * 新增用户违规
     * @param UserViolation
     * @return
     */
    @Override
    public UserViolationResult add(UserViolation UserViolation) {
        UserViolation UserViolation1 = UserViolationRepository.save(UserViolation);
        return new UserViolationResult(CommonCode.SUCCESS,UserViolation1);
    }

    /**
     * 根据id修改用户违规
     * @param id
     * @param UserViolation
     * @return
     */
    @Override
    public UserViolationResult updateById(String id, UserViolation UserViolation) {
        //根据Id查询用户违规
        UserViolation UserViolation1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(UserViolation1!=null){

            UserViolationRepository.save(UserViolation1);
            UserViolation UserViolation2 = UserViolationRepository.save(UserViolation1);
            if(UserViolation2!=null){
                return new UserViolationResult(CommonCode.SUCCESS,UserViolation2);
            }

        }
        //若不存在，则返回失败
        return new UserViolationResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除用户违规
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询用户违规
        UserViolation UserViolation1 = this.findById(id);
        if(UserViolation1!=null){
            //若存在，删除用户违规
            UserViolationRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询用户违规
     * @param id
     * @return
     */
    @Override
    public UserViolation findById(String id) {
        Optional<UserViolation> optional = UserViolationRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
