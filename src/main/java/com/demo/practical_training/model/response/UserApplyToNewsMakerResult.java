package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.UserApplyToNewsMaker;
import lombok.Data;

/**
 * 用户申请成为新闻发布者数据模型
 */
@Data
public class UserApplyToNewsMakerResult extends ResponseResult {
    UserApplyToNewsMaker userApplyToNewsMaker;

    public UserApplyToNewsMakerResult(ResultCode resultCode, UserApplyToNewsMaker userApplyToNewsMaker) {
        super(resultCode);
        this.userApplyToNewsMaker = userApplyToNewsMaker;
    }
}
