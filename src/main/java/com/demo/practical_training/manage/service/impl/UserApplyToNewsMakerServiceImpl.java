package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.UserApplyToNewsMakerRepository;
import com.demo.practical_training.entity.UserApplyToNewsMaker;
import com.demo.practical_training.entity.dto.UserApplyToNewsMakerDTO;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.manage.service.UserApplyToNewsMakerService;
import com.demo.practical_training.model.request.QueryUserApplyToNewsMakerRequest;
import com.demo.practical_training.model.response.UserApplyToNewsMakerResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
@Transactional()
public class UserApplyToNewsMakerServiceImpl implements UserApplyToNewsMakerService {
    @Autowired
    UserApplyToNewsMakerRepository UserApplyToNewsMakerRepository;
    @Autowired
    NewsService newsService;

    /**
     * 分页和排序加动态查询实页面
     *
     * @param pageRequest
     * @param queryUserApplyToNewsMakerRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserApplyToNewsMakerRequest queryUserApplyToNewsMakerRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        //模糊匹配别名
//        exampleMatcher = exampleMatcher.withMatcher("UserApplyToNewsMakerTitle", ExampleMatcher.GenericPropertyMatchers.contains());
//        //创建条件值对象
        UserApplyToNewsMaker UserApplyToNewsMaker = new UserApplyToNewsMaker();
        UserApplyToNewsMaker.setReviewState(0);
//        //判断实名认证标题是否为空
//        if (StringUtils.isNotEmpty(queryUserApplyToNewsMakerRequest.getUserApplyToNewsMakerName())) {
//            UserApplyToNewsMaker.setUserApplyToNewsMakerName(queryUserApplyToNewsMakerRequest.getUserApplyToNewsMakerName());
//        }
        //判断实名认证id是否为空
        if (StringUtils.isNotEmpty(queryUserApplyToNewsMakerRequest.getId())) {
            UserApplyToNewsMaker.setId(queryUserApplyToNewsMakerRequest.getId());
        }
        //创建条件实例对象
        Example<UserApplyToNewsMaker> example = Example.of(UserApplyToNewsMaker, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<UserApplyToNewsMaker> all = UserApplyToNewsMakerRepository.findAll(example, pageRequest.getPageable());
        //新建QueryResult<T> 对象
        List<UserApplyToNewsMakerDTO> list = new ArrayList<>();

        for (UserApplyToNewsMaker userApplyToNewsMaker : all) {
            UserApplyToNewsMakerDTO UserApplyToNewsMakerDTO = new UserApplyToNewsMakerDTO();
            UserApplyToNewsMakerDTO.setId(userApplyToNewsMaker.getId());
            UserApplyToNewsMakerDTO.setUserId(userApplyToNewsMaker.getUser().getId());
            UserApplyToNewsMakerDTO.setUserName(userApplyToNewsMaker.getUser().getUserName());
            UserApplyToNewsMakerDTO.setReason(userApplyToNewsMaker.getReason());
            UserApplyToNewsMakerDTO.setRegistrationTime(userApplyToNewsMaker.getVerifiedTime().toString());
            list.add(UserApplyToNewsMakerDTO);
        }
        QueryResult<UserApplyToNewsMakerDTO> userApplyToNewsMakerDTOQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        userApplyToNewsMakerDTOQueryResult.setList(list);
        userApplyToNewsMakerDTOQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, userApplyToNewsMakerDTOQueryResult);
    }

    /**
     * 新增实名认证
     * @param UserApplyToNewsMaker
     * @return
     */
    @Override
    public UserApplyToNewsMakerResult add(UserApplyToNewsMaker UserApplyToNewsMaker) {
        UserApplyToNewsMaker UserApplyToNewsMaker1 = UserApplyToNewsMakerRepository.save(UserApplyToNewsMaker);
        return new UserApplyToNewsMakerResult(CommonCode.SUCCESS,UserApplyToNewsMaker1);
    }

    /**
     * 根据id修改实名认证
     * @param id
     * @param UserApplyToNewsMaker
     * @return
     */
    @Override
    public UserApplyToNewsMakerResult updateById(String id, UserApplyToNewsMaker UserApplyToNewsMaker) {
        //根据Id查询实名认证
        UserApplyToNewsMaker UserApplyToNewsMaker1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(UserApplyToNewsMaker1!=null){

            UserApplyToNewsMakerRepository.save(UserApplyToNewsMaker1);
            UserApplyToNewsMaker UserApplyToNewsMaker2 = UserApplyToNewsMakerRepository.save(UserApplyToNewsMaker1);
            if(UserApplyToNewsMaker2!=null){
                return new UserApplyToNewsMakerResult(CommonCode.SUCCESS,UserApplyToNewsMaker2);
            }

        }
        //若不存在，则返回失败
        return new UserApplyToNewsMakerResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除实名认证
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询实名认证
        UserApplyToNewsMaker UserApplyToNewsMaker1 = this.findById(id);
        if(UserApplyToNewsMaker1!=null){
            //若存在，删除实名认证
            UserApplyToNewsMakerRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询实名认证
     * @param id
     * @return
     */
    @Override
    public UserApplyToNewsMaker findById(String id) {
        Optional<UserApplyToNewsMaker> optional = UserApplyToNewsMakerRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
