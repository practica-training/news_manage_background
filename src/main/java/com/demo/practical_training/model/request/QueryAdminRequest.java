package com.demo.practical_training.model.request;

import com.demo.practical_training.common.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * 封装管理员条件查询对象
 */
@Data
@ToString
public class QueryAdminRequest extends RequestData {
    //管理员名
    private String adminName;

}
