package com.demo.practical_training.common.request;

import lombok.Data;
import lombok.ToString;

/**
 * 请求类型统一继承RequestData类型，方便扩展
 */
@Data
@ToString
public class RequestData {
    private String id;
}
