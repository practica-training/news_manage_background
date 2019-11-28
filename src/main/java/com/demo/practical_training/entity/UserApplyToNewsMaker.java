package com.demo.practical_training.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class UserApplyToNewsMaker {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "applyid", unique = true, nullable = false, length = 20)
    private String applyID;
    @OneToOne
    @JoinColumn(name = "userID")
    private User user;
    /**
     * 申请原因
     */
    private String reason;
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
