package com.demo.practical_training.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 新闻标签
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
public class NewsLabel {
    /**
     * 标签ID
     */
    @Id
    private String labelID;
    /**
     * 标签名
     */
    private String name;

    public NewsLabel() {
    }

    public NewsLabel(String labelID, String name) {
        this.labelID = labelID;
        this.name = name;
    }

    public String getLabelID() {
        return labelID;
    }

    public void setLabelID(String labelID) {
        this.labelID = labelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
