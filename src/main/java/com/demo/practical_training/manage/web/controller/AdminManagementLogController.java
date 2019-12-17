package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.manage.service.AdminManagementLogService;
import com.demo.practical_training.model.request.QueryAdminManagementLogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/adminManagementLog")
public class AdminManagementLogController {
    @Autowired
    AdminManagementLogService adminManagementLogService;

    /**
     * 分页排序条件查询管理员管理日志列表
     * @param pageRequest
     * @param queryAdminManagementLogRequest
     * @return
     */
    @GetMapping("/findList")
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryAdminManagementLogRequest queryAdminManagementLogRequest){
        return adminManagementLogService.findList(pageRequest,queryAdminManagementLogRequest);
    }

}
