package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.MessageRepository;
import com.demo.practical_training.dao.UserRepository;
import com.demo.practical_training.entity.Message;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.manage.service.MessageService;
import com.demo.practical_training.model.request.QueryMessageRequest;
import com.demo.practical_training.model.response.MessageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional()
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    /**
     * 分页和排序加动态查询消息页面
     *
     * @param pageRequest
     * @param queryMessageRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryMessageRequest queryMessageRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        //模糊匹配别名
//        exampleMatcher = exampleMatcher.withMatcher("MessageTitle", ExampleMatcher.GenericPropertyMatchers.contains());
//        //创建条件值对象
        Message Message = new Message();
//        //判断消息标题是否为空
//        if (StringUtils.isNotEmpty(queryMessageRequest.getMessageName())) {
//            Message.setMessageName(queryMessageRequest.getMessageName());
//        }
        //判断消息id是否为空
        if (StringUtils.isNotEmpty(queryMessageRequest.getId())) {
            Message.setId(queryMessageRequest.getId());
        }
        //创建条件实例对象
        Example<Message> example = Example.of(Message, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<Message> all = messageRepository.findAll(example,pageRequest.getPageable());
        //新建QueryResult<T> 对象
        QueryResult<Message> MessageQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        MessageQueryResult.setList(all.getContent());
        MessageQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, MessageQueryResult);
    }
    /**
     * 新增消息
     * @return
     */
    public void addMessage(String fromId, User user, String context){
        Message message = new Message();
        message.setContent(context);
        message.setUser(user);
        messageRepository.save(message);
    }

    @Override
    public void deleteMessage(String fromId, User user, String context) {

    }

    /**
     * 新增消息
     * @param Message
     * @return
     */
    @Override
    public MessageResult add(Message Message) {
        Message Message1 = messageRepository.save(Message);
        return new MessageResult(CommonCode.SUCCESS,Message1);
    }

    /**
     * 根据id修改消息
     * @param id
     * @param Message
     * @return
     */
    @Override
    public MessageResult updateById(String id, Message Message) {
        //根据Id查询消息
        Message Message1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(Message1!=null){

            messageRepository.save(Message1);
            Message Message2 = messageRepository.save(Message1);
            if(Message2!=null){
                return new MessageResult(CommonCode.SUCCESS,Message2);
            }

        }
        //若不存在，则返回失败
        return new MessageResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除消息
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询消息
        Message Message1 = this.findById(id);
        if(Message1!=null){
            //若存在，删除消息
            messageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询消息
     * @param id
     * @return
     */
    @Override
    public Message findById(String id) {
        Optional<Message> optional = messageRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    /**
     * 根据用户id查询消息
     * @param id
     * @return
     */
    @Override
    public List<Message> findByUserId(String id) {
        List<Message> list = messageRepository.findByToid(id);
        if(list!=null){
            return list;
        }
        return null;
    }

    @Override
    public void missNewsMessage(User user, String context) {

    }

    @Override
    public void downNewsMesssage(String fromId, User user, String context) {

    }
}
