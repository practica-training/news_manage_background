package com.demo.practical_training.entity.dto;

import lombok.Data;

/**
 * 封装实名认证返回对象
 */
@Data
public class UserApplyToNewsMakerDTO {
//    {
// *              id:"申请的id",
// *              userId:"申请新闻发布者的用户id",
// *              userName:"新闻发布者姓名",
// *              registrationTime:"新闻发布者的注册时间",
// *              idCard:"新闻发布者的身份证号码",
// *              realName:"新闻发布者的真实姓名",
// *              reason:"申请原因"
//            *          }
    private String id;
    private String userId;
    private String userName;
    private String registrationTime;
    private String idCard;
    private String realName;
    private String reason;

    //提供返回结果的业务层

}
