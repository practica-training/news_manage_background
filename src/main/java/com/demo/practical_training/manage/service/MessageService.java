package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Message;
import com.demo.practical_training.entity.User;
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
    //发送新闻举报忽略消息
    public void missNewsMessage(User user,String context);
    //向用户发送新闻下架消息
    public void downNewsMesssage(String fromId, User user ,String context);
    //发送用户禁言消息
    public void addMessage(String fromId, User user, String context);
    //发送用户解除禁言消息
    public void deleteMessage(String fromId, User user, String context);

}
