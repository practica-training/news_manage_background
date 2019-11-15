package com.demo.practical_training.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

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

    public NewsViolation() {
    }

    public NewsViolation(String violationID, String newsID, String violationReason) {
        this.violationID = violationID;
        this.newsID = newsID;
        this.violationReason = violationReason;
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
}
