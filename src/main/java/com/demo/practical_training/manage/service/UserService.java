package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.model.request.QueryUserRequest;
import com.demo.practical_training.model.response.UserResult;

/**
 * 用户业务层
 */
public interface UserService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserRequest queryUserRequest);
    //增加用户
    public UserResult add(User User);
    //修改用户
    public UserResult updateById(String id,User User);
    //删除用户
    public ResponseResult deleteById(String id);
    //根据id查询用户
    public User findById(String id);
}