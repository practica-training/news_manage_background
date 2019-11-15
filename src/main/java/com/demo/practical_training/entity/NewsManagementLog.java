package com.demo.practical_training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
/**
 * 管理员的新闻管理日志
 */
public class NewsManagementLog {
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
     * 新闻ID
     */
    private String newsID;
    /**
     * 处理内容，管理员对这个新闻做了什么
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
    public NewsManagementLog() {
    }

    /**
     * 全参构造器
     * @param logID 日志ID
     * @param adminID 管理员ID
     * @param newsID 新闻ID
     * @param operationalContent 处理内容
     * @param processingTime 处理时间
     */
    public NewsManagementLog(String logID, String adminID, String newsID, String operationalContent, Timestamp processingTime) {
        this.logID = logID;
        this.adminID = adminID;
        this.newsID = newsID;
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
     * 获取新闻ID
     * @return
     */
    public String getNewsID() {
        return newsID;
    }

    /**
     * 设置新闻ID
     * @param newsID
     */
    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    /**
     * 获取操作内容
     * @return
     */
    public String getOperationalContent() {
        return operationalContent;
    }

    /**
     * 设置操作内容
     * @param operationalContent
     */
    public void setOperationalContent(String operationalContent) {
        this.operationalContent = operationalContent;
    }

    /**
     * 获取操作时间
     * @return
     */
    public Timestamp getProcessingTime() {
        return processingTime;
    }

    /**
     * 设置操作时间
     * @param processingTime
     */
    public void setProcessingTime(Timestamp processingTime) {
        this.processingTime = processingTime;
    }
}
