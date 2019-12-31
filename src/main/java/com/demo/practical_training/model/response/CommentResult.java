package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.Comment;
import lombok.Data;

/**
 * 用户违规数据模型
 */
@Data
public class CommentResult extends ResponseResult {
    Comment comment;

    public CommentResult(ResultCode resultCode, Comment comment) {
        super(resultCode);
        this.comment = comment;
    }
}
