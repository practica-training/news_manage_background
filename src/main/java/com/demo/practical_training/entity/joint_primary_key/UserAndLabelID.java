package com.demo.practical_training.entity.joint_primary_key;

import java.io.Serializable;

/**
 * 新闻标签和用户对应表的复合主键
 */
public class UserAndLabelID implements Serializable {
    private String userID;
    private String labelID;
}
