package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.UserManagementLog;
import lombok.Data;

@Data
public class UserManagementLogResult extends ResponseResult {
    UserManagementLog userManagementLog;

    public UserManagementLogResult(ResultCode resultCode, UserManagementLog userManagementLog) {
        super(resultCode);
        this.userManagementLog = userManagementLog;
    }
}
