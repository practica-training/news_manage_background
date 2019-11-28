package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.AdminManagementLog;
import com.demo.practical_training.model.request.QueryAdminManagementLogRequest;
import com.demo.practical_training.model.response.AdminManagementLogResult;

/**
 * 超级管理员管理普通管理员的日志业务层
 */
public interface AdminManagementLogService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryAdminManagementLogRequest queryAdminManagementLogRequest);
    //增加超级管理员管理普通管理员的日志
    public AdminManagementLogResult add(AdminManagementLog AdminManagementLog);
    //修改超级管理员管理普通管理员的日志
    public AdminManagementLogResult updateById(String id, AdminManagementLog AdminManagementLog);
    //删除超级管理员管理普通管理员的日志
    public ResponseResult deleteById(String id);
    //根据id查询超级管理员管理普通管理员的日志
    public AdminManagementLog findById(String id);
}
