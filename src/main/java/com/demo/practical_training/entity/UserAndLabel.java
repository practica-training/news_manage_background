package com.demo.practical_training.entity;

import com.demo.practical_training.entity.joint_primary_key.UserAndLabelID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * 用户和新闻标签对应表
 */
@Entity
@IdClass(UserAndLabelID.class)
public class UserAndLabel {
    /**
     * 用户ID
     */
    @Id
    private String userID;
    /**
     * 标签ID
     */
    @Id
    private String labelID;

    public UserAndLabel() {
    }

    public UserAndLabel(String userID, String labelID) {
        this.userID = userID;
        this.labelID = labelID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLabelID() {
        return labelID;
    }

    public void setLabelID(String labelID) {
        this.labelID = labelID;
    }
}
