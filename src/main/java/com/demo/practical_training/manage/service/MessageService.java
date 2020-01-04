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

    //举报成功向用户发送新闻下架消息
    public void downNewsMessage(String fromId, User user ,String context);
    //发送新闻审核成功消息
    public void passNewsMessage(String fromId, User user, String context);
    //发送新闻审核失败消息
    public void refuseNewsMessage(String fromId, User user, String context, String offReason);
    //向用户发送新闻下架消息
    public void downSuccessMessage(String fromId, User user ,String context, String offReason);
    //向用户发送新闻解除下架消息
    public void downRemoveMessage(String fromId, User user ,String context);
    //发送用户禁言消息
    public void addMessage(String fromId, User user, String context);

    public void addMessage(String fromId, User user, String context, String offReason);
    //发送用户解除禁言消息
    public void deleteMessage(String fromId, User user, String context);
    //发送用户审核通过消息
    public void passUserMessage(String fromId, User user, String context);
    //发送用户审核不通过消息
    public void refuseUserMessage(String fromId, User user, String context, String offReason);
    //发送用户成为新闻发布者通过消息
    public void passPublisherMessage(String fromId, User user, String context);
    //发送用户成为新闻发布者失败消息
    public void refusePublisherMessage(String fromId, User user, String context, String offReason);
}
