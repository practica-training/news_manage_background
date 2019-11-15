package com.demo.practical_training.entity;


import com.demo.practical_training.entity.joint_primary_key.NewsAndTypeID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * 新闻和类型对应的表
 */
@Entity
@IdClass(NewsAndTypeID.class)
public class NewsAndType {
    /**
     * 新闻ID
     */
    @Id
    private String newsID;
    /**
     * 标签ID
     */
    @Id
    private String typeID;

    public NewsAndType() {
    }

    public NewsAndType(String newsID, String typeID) {
        this.newsID = newsID;
        this.typeID = typeID;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }
}
