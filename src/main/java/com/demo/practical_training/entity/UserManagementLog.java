package com.demo.practical_training.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 管理员的用户管理日志
 */
@Entity
@Data
public class UserManagementLog {
    /**
     * 日志ID
     */
    @Id
    private String logID;
    /**
     * 管理ID
     */
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "adminID")
    private Admin admin;
    /**
     * 用户ID
     */
    @JoinColumn(name = "userID")
    @OneToOne
    private User user;
    /**
     * 处理内容，管理员对这个用户做了什么
     */
    private String operationalContent;
    /**
     * 处理时间
     */
    @Column(columnDefinition="TIMESTAMP")
    private Timestamp processingTime;
}
