package com.demo.practical_training.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 实名认证表
 */
@Entity
@Data
public class UserVerified {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "id", unique = true, nullable = false, length = 20)
    String id;
    @OneToOne
    @JoinColumn(name = "userID")
    private User user;
    /**
     * 身份证号码
     */
    private String idCard;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手持身份证照片
     */
    private String photo;
    /**
     * 审核状态 0等待审核 1通过审核 -1审核失败
     */
    private Integer reviewState;
    /**
     * 失败原因（当审核失败时有）
     */
    private String failureReason;
    /**
     * 审核时间（审核状态改变一次就刷新一次）
     */
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp verifiedTime;

}
