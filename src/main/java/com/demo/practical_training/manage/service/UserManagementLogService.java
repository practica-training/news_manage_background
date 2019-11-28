package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.UserManagementLog;
import com.demo.practical_training.model.request.QueryUserManagementLogRequest;
import com.demo.practical_training.model.response.UserManagementLogResult;

/**
 * 管理员的用户管理日志业务层
 */
public interface UserManagementLogService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserManagementLogRequest queryUserManagementLogRequest);
    //增加管理员的用户管理日志
    public UserManagementLogResult add(UserManagementLog UserManagementLog);
    //修改管理员的用户管理日志
    public UserManagementLogResult updateById(String id, UserManagementLog UserManagementLog);
    //删除管理员的用户管理日志
    public ResponseResult deleteById(String id);
    //根据id查询管理员的用户管理日志
    public UserManagementLog findById(String id);
}
