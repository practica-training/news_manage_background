package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.Message;
import lombok.Data;

/**
 * 消息数据模型
 */
@Data
public class MessageResult extends ResponseResult {
    Message message1;

    public MessageResult(ResultCode resultCode,  Message  message1) {
        super(resultCode);
        this. message1 =  message1;
    }
}
