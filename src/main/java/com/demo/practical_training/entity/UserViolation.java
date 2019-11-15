package com.demo.practical_training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 用户违规表
 */
@Entity
public class UserViolation {
    /**
     * 违规ID
     */
    @Id
    private String violationID;
    /**
     * 违规的用户ID
     */
    private String userID;
    /**
     * 举报者ID（如果是其他用户举报的则有）
     */
    private String reporterID;
    /**
     * 违规的原因
     */
    private String reason;
    /**
     * 处罚决定 1禁言 2封号
     */
    private Integer penaltyDecision;
    /**
     *处罚时长 1为3天,2为7天，3为15天，4为永久（可改）
     */
    private Integer duration;
    /**
     * 结束时间（在这个时间后，将用户状态设为正常）
     */
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp endTime;

    public UserViolation() {
    }

    /**
     *
     * @param violationID
     * @param userID
     * @param reporterID
     * @param reason
     * @param penaltyDecision
     * @param duration
     * @param endTime
     */
    public UserViolation(String violationID, String userID,String reporterID,String reason, Integer penaltyDecision, Integer duration, Timestamp endTime) {
        this.violationID = violationID;
        this.userID = userID;
        this.reporterID = reporterID;
        this.reason = reason;
        this.penaltyDecision = penaltyDecision;
        this.duration = duration;
        this.endTime = endTime;
    }

    public String getViolationID() {
        return violationID;
    }

    public void setViolationID(String violationID) {
        this.violationID = violationID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getPenaltyDecision() {
        return penaltyDecision;
    }

    public void setPenaltyDecision(Integer penaltyDecision) {
        this.penaltyDecision = penaltyDecision;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
