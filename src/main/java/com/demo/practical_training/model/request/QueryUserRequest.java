package com.demo.practical_training.model.request;

import com.demo.practical_training.common.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * 封装用户条件查询对象
 */
@Data
@ToString
public class QueryUserRequest extends RequestData {
    //用户id
    private String userID;
    //用户名
    private String userName;
}
