package com.demo.practical_training.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 超级管理员管理普通管理员的日志
 */
@Entity
@Data
public class AdminManagementLog {
    /**
     * 日志ID
     */
    @Id
    @Column(columnDefinition = "CHAR(33)")
    private String logID;
    /**
     * 被超级管理员管理的管理员ID
     */
    @OneToOne
    @JoinColumn(name = "adminID")
    private Admin admin;
    /**
     * 处理内容，超级管理员对这个管理员做了什么
     */
    private String operationalContent;
    /**
     * 处理时间
     */
    @Column(columnDefinition="TIMESTAMP")
    private Timestamp processingTime;


}
