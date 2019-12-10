package com.demo.practical_training.entity.dto;

import lombok.Data;

/**
 * 封装实名认证返回对象
 */
@Data
public class UserManageDTO {
//    {
//     *           userId: "用户id",
//     *           userName: "用户姓名",
//     *           violationNumber: "违规次数",
//     *           userState: "是否违规",
//     *           normalDate: "恢复正常能够说话的日子",
//     *           registrationTime: "注册时间"
//            *              注意注册时间格式为yyyy-mm-dd
//            *          },
    private String userId;
    private String userName;
    private Integer violationNumber;
    private Integer userState;
    private String normalDate;
    private String registrationTime;


    //提供返回结果的业务层

}
