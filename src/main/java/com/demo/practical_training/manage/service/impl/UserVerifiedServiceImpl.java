package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.UserVerifiedRepository;
import com.demo.practical_training.entity.UserVerified;
import com.demo.practical_training.entity.dto.UserVerifiedDTO;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.manage.service.UserVerifiedService;
import com.demo.practical_training.model.request.QueryUserVerifiedRequest;
import com.demo.practical_training.model.response.UserVerifiedResult;
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
public class UserVerifiedServiceImpl implements UserVerifiedService {
    @Autowired
    UserVerifiedRepository UserVerifiedRepository;
    @Autowired
    NewsService newsService;
    /**
     * 分页和排序加动态查询实名认证页面
     *
     * @param pageRequest
     * @param queryUserVerifiedRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserVerifiedRequest queryUserVerifiedRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        //模糊匹配别名
//        exampleMatcher = exampleMatcher.withMatcher("UserVerifiedTitle", ExampleMatcher.GenericPropertyMatchers.contains());
//        //创建条件值对象
        UserVerified UserVerified = new UserVerified();
        UserVerified.setReviewState(0);
//        //判断实名认证标题是否为空
//        if (StringUtils.isNotEmpty(queryUserVerifiedRequest.getUserVerifiedName())) {
//            UserVerified.setUserVerifiedName(queryUserVerifiedRequest.getUserVerifiedName());
//        }
        //判断实名认证id是否为空
        if (StringUtils.isNotEmpty(queryUserVerifiedRequest.getId())) {
            UserVerified.setId(queryUserVerifiedRequest.getId());
        }
        //创建条件实例对象
        Example<UserVerified> example = Example.of(UserVerified, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<UserVerified> all = UserVerifiedRepository.findAll(example, pageRequest.getPageable());
        //新建QueryResult<T> 对象
        List<UserVerifiedDTO> list = new ArrayList<>();

        for (UserVerified userVerified : all) {
            UserVerifiedDTO userVerifiedDTO = new UserVerifiedDTO();
            userVerifiedDTO.setId(userVerifiedDTO.getId());
            userVerifiedDTO.setUserName(userVerified.getUser().getUserName());
            userVerifiedDTO.setIdCard(userVerified.getIdCard());
            userVerifiedDTO.setPhoto(userVerified.getPhoto());
            userVerifiedDTO.setRealName(userVerified.getRealName());
            userVerifiedDTO.setRegistrationTime(userVerified.getVerifiedTime().toString());
            list.add(userVerifiedDTO);
        }
        QueryResult<UserVerifiedDTO> userVerifiedDTOQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        userVerifiedDTOQueryResult.setList(list);
        userVerifiedDTOQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, userVerifiedDTOQueryResult);
    }

    /**
     * 新增实名认证
     * @param UserVerified
     * @return
     */
    @Override
    public UserVerifiedResult add(UserVerified UserVerified) {
        UserVerified UserVerified1 = UserVerifiedRepository.save(UserVerified);
        return new UserVerifiedResult(CommonCode.SUCCESS,UserVerified1);
    }

    /**
     * 根据id修改实名认证
     * @param id
     * @param UserVerified
     * @return
     */
    @Override
    public UserVerifiedResult updateById(String id, UserVerified UserVerified) {
        //根据Id查询实名认证
        UserVerified UserVerified1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(UserVerified1!=null){

            UserVerifiedRepository.save(UserVerified1);
            UserVerified UserVerified2 = UserVerifiedRepository.save(UserVerified1);
            if(UserVerified2!=null){
                return new UserVerifiedResult(CommonCode.SUCCESS,UserVerified2);
            }

        }
        //若不存在，则返回失败
        return new UserVerifiedResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除实名认证
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询实名认证
        UserVerified UserVerified1 = this.findById(id);
        if(UserVerified1!=null){
            //若存在，删除实名认证
            UserVerifiedRepository.deleteById(id);
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
    public UserVerified findById(String id) {
        Optional<UserVerified> optional = UserVerifiedRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
