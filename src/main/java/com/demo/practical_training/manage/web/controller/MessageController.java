package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Message;
import com.demo.practical_training.manage.service.MessageService;
import com.demo.practical_training.model.request.QueryMessageRequest;
import com.demo.practical_training.model.response.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消息控制层
 */
@RestController
@RequestMapping("/manage/message")
public class MessageController {
    @Autowired
    MessageService MessageService;

    /**
     * 分页排序条件查询消息列表
     * @param pageRequest
     * @param queryMessageRequest
     * @return
     */
    @GetMapping
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryMessageRequest queryMessageRequest){
        return MessageService.findList(pageRequest,queryMessageRequest);
    }

    /**
     * 新增消息
     * @param Message
     * @return
     */
    @PostMapping
    public MessageResult add(@RequestBody Message Message){
            return MessageService.add(Message);
    }

    /**
     * 更新消息
     * @param id
     * @param Message
     * @return
     */
    @PutMapping("/id/{id}")
    public MessageResult update(@PathVariable("id") String id, @RequestBody Message Message){
        return MessageService.updateById(id,Message);
    }

    /**
     * 删除消息
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return MessageService.deleteById(id);
    }

    /**
     * 根据id查询消息
     * @param id
     * @return
     */
    @GetMapping("/getMessage/{id}")
    public Message findOne(@PathVariable("id") String id){
        return MessageService.findById(id);
    }

    /**
     * 获取用户消息列表
     * @param id
     * @return
     */
    @GetMapping("/getUserMessage/{id}")
    public List<Message> findList(@PathVariable("id") String id){
        return MessageService.findByUserId(id);
    }
}
