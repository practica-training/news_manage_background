package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.manage.service.UserManagementLogService;
import com.demo.practical_training.model.request.QueryUserManagementLogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/userManagementLog")
public class UserManagementLogController {
    @Autowired
    UserManagementLogService userManagementLogService;

    /**
     * 分页排序条件查询用户管理日志列表
     * @param pageRequest
     * @param queryUserManagementLogRequest
     * @return
     */
    @GetMapping("/findList")
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserManagementLogRequest queryUserManagementLogRequest){
        return userManagementLogService.findList(pageRequest,queryUserManagementLogRequest);
    }


}
