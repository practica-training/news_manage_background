package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.NewsManagementLog;

public class NewsManagementLogResult extends ResponseResult {
    NewsManagementLog newsManagementLog;

    public NewsManagementLogResult(ResultCode resultCode, NewsManagementLog newsManagementLog) {
        super(resultCode);
        this.newsManagementLog = newsManagementLog;
    }
}
