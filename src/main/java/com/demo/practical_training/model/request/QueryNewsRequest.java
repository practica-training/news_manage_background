package com.demo.practical_training.model.request;

import com.demo.practical_training.common.request.RequestData;
import com.demo.practical_training.entity.User;
import lombok.Data;
import lombok.ToString;

/**
 * 封装新闻条件查询对象
 */
@Data
@ToString
public class QueryNewsRequest extends RequestData {
    //新闻标题
    private String newsTitle;
    private Integer page=1;
    private Integer newsState;
    private User user;
}
