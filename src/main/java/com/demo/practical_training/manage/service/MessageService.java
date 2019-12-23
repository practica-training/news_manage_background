package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Message;
import com.demo.practical_training.model.request.QueryMessageRequest;
import com.demo.practical_training.model.response.MessageResult;

import java.util.List;

/**
 * 消息业务层
 */
public interface MessageService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryMessageRequest queryMessageRequest);
    //增加消息
    public MessageResult add(Message Message);
    //修改消息
    public MessageResult updateById(String id, Message Message);
    //删除消息
    public ResponseResult deleteById(String id);
    //根据id查询消息
    public Message findById(String id);
    //根据用户id查询消息
    public List<Message> findByUserId(String id);
}
