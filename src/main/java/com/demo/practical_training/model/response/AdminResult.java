package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.Admin;
import lombok.Data;

/**
 * 管理员数据模型
 */
@Data
public class AdminResult extends ResponseResult {
    Admin admin;

    public AdminResult(ResultCode resultCode, Admin admin) {
        super(resultCode);
        this.admin = admin;
    }
}
