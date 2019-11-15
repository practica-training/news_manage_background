package com.demo.practical_training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 新闻举报
 */
@Entity
public class NewsReport {
    /**
     * 举报ID
     */
    @Id
    private String reportID;
    /**
     * 举报者ID
     */
    private String userID;
    /**
     * 被举报的新闻ID
     */
    private String newsID;
    /**
     * 举报原因
     */
    private String reportReason;
    /**
     * 举报时间
     */
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp reportTime;
    /**
     * 审核状态 0等待审核 1审核完成
     */
    private Integer reviewState;
    /**
     * 是否违规 0没有违规 1违规
     */
    private Integer isIllegal;

    public NewsReport() {
    }

    public NewsReport(String reportID, String userID, String newsID, String reportReason, Timestamp reportTime, Integer reviewState, Integer isIllegal) {
        this.reportID = reportID;
        this.userID = userID;
        this.newsID = newsID;
        this.reportReason = reportReason;
        this.reportTime = reportTime;
        this.reviewState = reviewState;
        this.isIllegal = isIllegal;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public Timestamp getReportTime() {
        return reportTime;
    }

    public void setReportTime(Timestamp reportTime) {
        this.reportTime = reportTime;
    }

    public Integer getReviewState() {
        return reviewState;
    }

    public void setReviewState(Integer reviewState) {
        this.reviewState = reviewState;
    }

    public Integer getIsIllegal() {
        return isIllegal;
    }

    public void setIsIllegal(Integer isIllegal) {
        this.isIllegal = isIllegal;
    }
}
