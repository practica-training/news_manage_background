package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.UserVerified;
import com.demo.practical_training.model.request.QueryUserVerifiedRequest;
import com.demo.practical_training.model.response.UserVerifiedResult;

import java.util.List;

/**
 * 实名认证表业务层
 */
public interface UserVerifiedService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserVerifiedRequest queryUserVerifiedRequest);
    //增加实名认证
    public UserVerifiedResult add(UserVerified UserVerified);
    //修改实名认证
    public UserVerifiedResult updateById(String id, UserVerified UserVerified);
    //删除实名认证
    public ResponseResult deleteById(String id);
    //根据id查询实名认证
    public UserVerified findById(String id);
    //根据用户id查询实名认证
    public List<UserVerified> findByUserid(String userid);
}
