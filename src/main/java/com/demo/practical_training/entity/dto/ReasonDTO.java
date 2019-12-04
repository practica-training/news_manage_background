package com.demo.practical_training.entity.dto;

import lombok.Data;

/**
 * 封装请求原因参数
 */
@Data
public class ReasonDTO {
    private String id;
    private String failureReason;
}
