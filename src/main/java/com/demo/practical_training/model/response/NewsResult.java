package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.dto.NewsDTO;
import lombok.Data;

/**
 * 新闻数据模型
 */
@Data
public class NewsResult extends ResponseResult {
    NewsDTO newsDTO;

    public NewsResult(ResultCode resultCode,  NewsDTO newsDTO) {
        super(resultCode);
        this.newsDTO = newsDTO;
    }
}
