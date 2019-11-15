package com.demo.practical_training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 新闻违规表
 */
@Entity
public class NewsViolation {
    /**
     * 违规ID
     */
    @Id
    private String violationID;
    /**
     * 新闻ID
     */
    private String newsID;
    /**
     * 违规原因
     */
    private String violationReason;
    /**
     * 审核时间
     */
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp reviewTime;

    public NewsViolation() {
    }

    public NewsViolation(String violationID, String newsID, String violationReason,Timestamp reviewTime) {
        this.violationID = violationID;
        this.newsID = newsID;
        this.violationReason = violationReason;
        this.reviewTime = reviewTime;
    }

    public String getViolationID() {
        return violationID;
    }

    public void setViolationID(String violationID) {
        this.violationID = violationID;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getViolationReason() {
        return violationReason;
    }

    public void setViolationReason(String violationReason) {
        this.violationReason = violationReason;
    }

    public Timestamp getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Timestamp reviewTime) {
        this.reviewTime = reviewTime;
    }
}
