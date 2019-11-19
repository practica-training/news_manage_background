package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.News;

public class NewsResult extends ResponseResult {
    News news;

    public NewsResult(ResultCode resultCode, News news) {
        super(resultCode);
        this.news = news;
    }
}
