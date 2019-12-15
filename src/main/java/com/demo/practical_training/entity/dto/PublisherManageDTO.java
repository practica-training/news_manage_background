package com.demo.practical_training.entity.dto;

import lombok.Data;

/**
 * 封装实名认证返回对象
 */
@Data
public class PublisherManageDTO {
// *          {
// *              userId:"新闻发布者id",
// *              userName:"新闻发布者名",
// *              registrationTime:"新闻发布者的注册时间",
// *              newsListSize:"发布的新闻数"
//                *          }
    private String userId;
    private String userName;
    private Integer newsListSize;
    private String registrationTime;
    //提供返回结果的业务层

}
