package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Comment;
import com.demo.practical_training.model.request.QueryCommentRequest;
import com.demo.practical_training.model.response.CommentResult;

/**
 * 评论表业务层
 */
public interface CommentService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryCommentRequest queryCommentRequest);
    //增加评论
    public  CommentResult add( Comment  Comment);
    //修改评论
    public  CommentResult updateById(String id,  Comment  Comment);
    //删除评论
    public ResponseResult deleteById(String id);
    //根据id查询评论
    public  Comment findById(String id);
}
