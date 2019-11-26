package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.NewsReport;
import lombok.Data;

/**
 * 新闻举报数据模型
 */
@Data
public class NewsReportResult extends ResponseResult {
    NewsReport NewsReport;

    public NewsReportResult(ResultCode resultCode, NewsReport NewsReport) {
        super(resultCode);
        this.NewsReport = NewsReport;
    }
}
