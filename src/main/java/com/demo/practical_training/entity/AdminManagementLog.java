package com.demo.practical_training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 超级管理员管理普通管理员的日志
 */
@Entity
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
    @Column(columnDefinition = "CHAR(33)")
    private String adminID;
    /**
     * 处理内容，超级管理员对这个管理员做了什么
     */
    private String operationalContent;
    /**
     * 处理时间
     */
    @Column(columnDefinition="TIMESTAMP")
    private Timestamp processingTime;

    public AdminManagementLog() {
    }

    public AdminManagementLog(String logID, String adminID, String operationalContent, Timestamp processingTime) {
        this.logID = logID;
        this.adminID = adminID;
        this.operationalContent = operationalContent;
        this.processingTime = processingTime;
    }

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getOperationalContent() {
        return operationalContent;
    }

    public void setOperationalContent(String operationalContent) {
        this.operationalContent = operationalContent;
    }

    public Timestamp getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Timestamp processingTime) {
        this.processingTime = processingTime;
    }
}
