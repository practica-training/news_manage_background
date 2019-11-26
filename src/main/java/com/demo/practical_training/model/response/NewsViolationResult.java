package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.NewsViolation;
import lombok.Data;

/**
 * 新闻违规数据模型
 */
@Data
public class NewsViolationResult extends ResponseResult {
    NewsViolation NewsViolation;

    public NewsViolationResult(ResultCode resultCode, NewsViolation NewsViolation) {
        super(resultCode);
        this.NewsViolation = NewsViolation;
    }
}
