package com.demo.practical_training.model.request;

import com.demo.practical_training.common.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * 封装用户举报条件查询对象
 */
@Data
@ToString
public class QueryUserReportRequest extends RequestData {
    //用户举报id
    private String reportID;
}
