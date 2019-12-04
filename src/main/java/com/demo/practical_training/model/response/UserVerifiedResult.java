package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.UserVerified;
import lombok.Data;

/**
 * 实名认证数据模型
 */
@Data
public class UserVerifiedResult extends ResponseResult {
    UserVerified UserVerified;

    public UserVerifiedResult(ResultCode resultCode, UserVerified UserVerified) {
        super(resultCode);
        this.UserVerified = UserVerified;
    }
}
