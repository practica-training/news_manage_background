package com.demo.practical_training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 实名认证表
 */
@Entity
public class UserVerified {
    /**
     * 用户ID
     */
    @Id
    private String userId;
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

    public UserVerified() {
    }

    public UserVerified(String userId, String idCard, String realName, String photo, Integer reviewState, String failureReason, Timestamp verifiedTime) {
        this.userId = userId;
        this.idCard = idCard;
        this.realName = realName;
        this.photo = photo;
        this.reviewState = reviewState;
        this.failureReason = failureReason;
        this.verifiedTime = verifiedTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getReviewState() {
        return reviewState;
    }

    public void setReviewState(Integer reviewState) {
        this.reviewState = reviewState;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public Timestamp getVerifiedTime() {
        return verifiedTime;
    }

    public void setVerifiedTime(Timestamp verifiedTime) {
        this.verifiedTime = verifiedTime;
    }
}
