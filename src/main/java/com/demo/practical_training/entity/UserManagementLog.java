package com.demo.practical_training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 管理员的用户管理日志
 */
@Entity
public class UserManagementLog {
    /**
     * 日志ID
     */
    @Id
    private String logID;
    /**
     * 管理员ID
     */
    private String adminID;
    /**
     * 用户ID
     */
    private String userID;
    /**
     * 处理内容，管理员对这个用户做了什么
     */
    private String operationalContent;
    /**
     * 处理时间
     */
    @Column(columnDefinition="TIMESTAMP")
    private Timestamp processingTime;

    /**
     * 无参构造器
     */
    public UserManagementLog() {
    }

    /**
     * 全参构造器
     * @param logID 日志ID
     * @param adminID 管理员ID
     * @param userID 用户ID
     * @param operationalContent 处理内容
     * @param processingTime 处理时间
     */
    public UserManagementLog(String logID, String adminID, String userID, String operationalContent, Timestamp processingTime) {
        this.logID = logID;
        this.adminID = adminID;
        this.userID = userID;
        this.operationalContent = operationalContent;
        this.processingTime = processingTime;
    }

    /**
     * 获取日志ID
     * @return
     */
    public String getLogID() {
        return logID;
    }

    /**
     * 设置日志ID
     * @param logID
     */
    public void setLogID(String logID) {
        this.logID = logID;
    }

    /**
     * 获取管理员ID
     * @return
     */
    public String getAdminID() {
        return adminID;
    }

    /**
     * 设置管理员ID
     * @param adminID
     */
    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    /**
     * 获取用户ID
     * @return
     */
    public String getUserID() {
        return userID;
    }

    /**
     * 设置用户ID
     * @param userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * 获取处理内容
     * @return
     */
    public String getOperationalContent() {
        return operationalContent;
    }

    /**
     * 设置处理内容
     * @param operationalContent
     */
    public void setOperationalContent(String operationalContent) {
        this.operationalContent = operationalContent;
    }

    /**
     * 获取处理时间
     * @return
     */
    public Timestamp getProcessingTime() {
        return processingTime;
    }

    /**
     * 设置处理时间
     * @param processingTime
     */
    public void setProcessingTime(Timestamp processingTime) {
        this.processingTime = processingTime;
    }
}
