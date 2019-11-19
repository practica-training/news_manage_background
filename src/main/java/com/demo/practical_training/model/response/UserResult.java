package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.User;
import lombok.Data;

/**
 * 用户数据模型
 */
@Data
public class UserResult extends ResponseResult {
    User user;

    public UserResult(ResultCode resultCode, User user) {
        super(resultCode);
        this.user = user;
    }
}
