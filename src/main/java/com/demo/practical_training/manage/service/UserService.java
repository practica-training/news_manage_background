package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.common.web.UserPageRequest;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.model.request.QueryUserRequest;
import com.demo.practical_training.model.response.UserResult;

import java.util.List;

/**
 * 用户业务层
 */
public interface UserService {
    //页面分页与查询 管理员新闻发布者
    public QueryResponseResult findPublicList(STablePageRequest pageRequest, QueryUserRequest queryUserRequest);
    //页面分页与查询 审核用户申请为新闻发布者
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserRequest queryUserRequest);
    //页面分页与查询 管理用户
    public QueryResponseResult findUserManageList(UserPageRequest pageRequest);
    //增加用户
    public UserResult add(User User);
    //修改用户
    public UserResult updateById(String id,User User);
    //删除用户
    public ResponseResult deleteById(String id);
    //根据id查询用户
    public User findById(String id);
    //根据用户名查询用户
    public List<User> findByUserName(String name);
    //根据昵称查询用户
    public List<User> findByUserNickname(String name);
    //根据手机号查询用户
    public List<User> findByUserPhone(String userPhone);
}
