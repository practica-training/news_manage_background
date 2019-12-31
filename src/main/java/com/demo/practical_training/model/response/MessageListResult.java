package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.Message;
import lombok.Data;

import java.util.List;

/**
 * 消息数据模型
 */
@Data
public class MessageListResult extends ResponseResult {
    List<Message> list;

    public MessageListResult(ResultCode resultCode, List<Message>  list) {
        super(resultCode);
        this. list =  list;
    }
}
