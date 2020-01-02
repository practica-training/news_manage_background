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
    CommentService CommentService;

    /**
     * 分页排序条件查询评论列表
     * @param pageRequest
     * @param queryCommentRequest
     * @return
     */
    @GetMapping
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryCommentRequest queryCommentRequest){
        return CommentService.findList(pageRequest,queryCommentRequest);
    }

    /**
     * 新增评论
     * @param Comment
     * @return
     */
    @PostMapping
    public CommentResult add(@RequestBody Comment Comment){
            return CommentService.add(Comment);
    }

    /**
     * 更新评论
     * @param id
     * @param Comment
     * @return
     */
    @PutMapping("/id/{id}")
    public CommentResult update(@PathVariable("id") String id, @RequestBody Comment Comment){
        return CommentService.updateById(id,Comment);
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return CommentService.deleteById(id);
    }

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public Comment findOne(@PathVariable("id") String id){
        return CommentService.findById(id);
    }
}
