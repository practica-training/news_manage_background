package com.demo.practical_training.entity.dto;

import lombok.Data;

import java.util.UUID;

/**
 * 封装用户举报返回对象
 */
@Data
public class UserReportDTO {
//     *          {
//     *          reportId: "举报id",
//    *           userName: "举报者",
//    *           reportedUserName: "被举报者1",
//    *           reportedUserId: "被举报者id1",
//    *           comment: "评论内容",
//    *           reason: "举报原因",
//    *           reportTime: "举报时间"
//                }
    private String id;
    private String reportId;
    private String userName;
    private String reportedUserName;
    private String reportedUserId;
    private String comment;
    private String reason;
    private String reportTime;

    //提供返回结果的业务层

    public String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public void setId(String id) {
        this.id = id;
    }

}
