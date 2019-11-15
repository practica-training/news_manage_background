package com.demo.practical_training.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 新闻类别
 */
@Entity
public class NewsType {
    /**
     * 类别ID
     */
    @Id
    private String typeID;
    /**
     * 名称
     */
    private String name;

    public NewsType() {
    }

    public NewsType(String typeID, String name) {
        this.typeID = typeID;
        this.name = name;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
