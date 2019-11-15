package com.demo.practical_training.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 新闻与用户对应表
 */
@Entity
public class NewsAndUser {
    /**
     * 新闻ID
     */
    @Id
    private String newsID;
    /**
     * 用户ID
     */
    private String userID;

    public NewsAndUser() {
    }

    public NewsAndUser(String newsID, String userID) {
        this.newsID = newsID;
        this.userID = userID;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
