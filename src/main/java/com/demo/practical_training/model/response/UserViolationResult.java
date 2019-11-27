package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.UserViolation;
import lombok.Data;

/**
 * 用户违规数据模型
 */
@Data
public class UserViolationResult extends ResponseResult {
    UserViolation UserViolation;

    public UserViolationResult(ResultCode resultCode, UserViolation UserViolation) {
        super(resultCode);
        this.UserViolation = UserViolation;
    }
}
