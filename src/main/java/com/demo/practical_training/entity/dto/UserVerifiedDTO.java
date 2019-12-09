package com.demo.practical_training.entity.dto;

import lombok.Data;

/**
 * 封装实名认证返回对象
 */
@Data
public class UserVerifiedDTO {
//    {
// *              userId:"申请实名认证的用户id",
// *              userName:"申请者姓名",
// *              registrationTime:"用户的注册时间",
// *              idCard:"申请者的身份证号码",
// *              realName:"申请者的真实姓名",
// *              photo:"申请者拍的身份证照片"
//     }
    private String userId;
    private String userName;
    private String registrationTime;
    private String idCard;
    private String realName;
    private String photo;

    //提供返回结果的业务层
}
