package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.AdminManagementLog;

public class AdminManagementLogResult extends ResponseResult {
    AdminManagementLog adminManagementLog;

    public AdminManagementLogResult(ResultCode resultCode, AdminManagementLog adminManagementLog) {
        super(resultCode);
        this.adminManagementLog = adminManagementLog;
    }
}
