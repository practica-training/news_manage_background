package com.demo.practical_training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 用户举报
 */
@Entity
public class UserReport {
    /**
     * 举报ID
     */
    @Id
    private String reportID;
    /**
     * 举报者ID
     */
    private String userId;
    /**
     * 被举报者ID
     */
    private String reportedID;
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

    public UserReport() {
    }

    public UserReport(String reportID, String userId, String reportedID, String reportReason, Timestamp reportTime, Integer reviewState, Integer isIllegal) {
        this.reportID = reportID;
        this.userId = userId;
        this.reportedID = reportedID;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReportedID() {
        return reportedID;
    }

    public void setReportedID(String reportedID) {
        this.reportedID = reportedID;
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
