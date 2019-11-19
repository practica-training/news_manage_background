package com.demo.practical_training.model.request;

import com.demo.practical_training.common.request.RequestData;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryNewsRequest extends RequestData {
    //新闻id
    private String newsId;
    //新闻标题
    private String newsTitle;
}
