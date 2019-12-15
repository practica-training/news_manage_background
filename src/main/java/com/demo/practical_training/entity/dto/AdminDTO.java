package com.demo.practical_training.entity.dto;

import lombok.Data;

/**
 * 封装新闻返回对象
 */
@Data
public class AdminDTO {

//    {
// *              adminId:"管理员id",
// *              adminName:"管理员用户名",
// *              power:"权力等级"
//            *          }
    private String adminId;
    private String adminName;
    private Integer power;
}
