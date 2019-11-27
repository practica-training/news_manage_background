package com.demo.practical_training.model.request;

import com.demo.practical_training.common.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * 封装新闻举报条件查询对象
 */
@Data
@ToString
public class QueryNewsReportRequest extends RequestData {
    //新闻举报id
    private String reportID;
}
