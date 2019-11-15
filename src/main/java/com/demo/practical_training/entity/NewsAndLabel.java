package com.demo.practical_training.entity;

import com.demo.practical_training.entity.joint_primary_key.NewsAndLabelID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * 新闻对应标签
 */
@Entity
@IdClass(NewsAndLabelID.class)
public class NewsAndLabel {
    /**
     * 新闻ID
     */
    @Id
    private String newsID;
    /**
     * 标签ID
     */
    @Id
    private String labelID;

    public NewsAndLabel() {
    }

    public NewsAndLabel(String newsID, String labelID) {
        this.newsID = newsID;
        this.labelID = labelID;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getLabelID() {
        return labelID;
    }

    public void setLabelID(String labelID) {
        this.labelID = labelID;
    }
}
