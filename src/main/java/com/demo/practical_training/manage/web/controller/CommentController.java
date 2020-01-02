package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Comment;
import com.demo.practical_training.manage.service.CommentService;
import com.demo.practical_training.model.request.QueryCommentRequest;
import com.demo.practical_training.model.response.CommentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论控制层
 */
@RestController
@RequestMapping("/manage/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 获得用户评论的内容
     * 接口：/manage/comment/userId/{userId}/{page}
     * @param userId 评论者id
     * @param page 页数 从1开始
     * @return
     */
    @GetMapping("/userId/{userId}/{page}")
    public QueryResponseResult findByUser(@PathVariable("userId")String userId,@PathVariable("page") Integer page){
//        System.out.println(userId + " " + page);
        return this.commentService.findListByUserId(userId,page);
    }

    /**
     * 获得别人回复的评论内容
     * 接口：/manage/comment/replyUserId/{replyUserId}/{page}
     * @param replyUserId 被回复者id
     * @param page 页数 从1开始
     * @return
     */
    @GetMapping("/replyUserId/{replyUserId}/{page}")
    public QueryResponseResult findByReplyUser(@PathVariable("replyUserId")String replyUserId,@PathVariable("page") Integer page){
//        System.out.println(replyUserId + " " + page);
        return this.commentService.findListByReplyUserId(replyUserId,page);
    }
    /**
     * 分页排序条件查询评论列表
     * @param pageRequest
     * @param queryCommentRequest
     * @return
     */
    @GetMapping
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryCommentRequest queryCommentRequest){
        return commentService.findList(pageRequest,queryCommentRequest);
    }

    /**
     * 新增评论
     * @param Comment
     * @return
     */
    @PostMapping
    public CommentResult add(@RequestBody Comment Comment){
            return commentService.add(Comment);
    }

    /**
     * 更新评论
     * @param id
     * @param Comment
     * @return
     */
    @PutMapping("/id/{id}")
    public CommentResult update(@PathVariable("id") String id, @RequestBody Comment Comment){
        return commentService.updateById(id,Comment);
    }

    /**
     * 接口：/manage/comment/id/{id}
     * 删除评论
     * @param id 评论的id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return commentService.deleteById(id);
    }

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public Comment findOne(@PathVariable("id") String id){
        return commentService.findById(id);
    }
}
