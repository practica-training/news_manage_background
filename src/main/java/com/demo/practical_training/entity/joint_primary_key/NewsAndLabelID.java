package com.demo.practical_training.entity.joint_primary_key;

import java.io.Serializable;

/**
 * 新闻标签对应表的复合主键
 */
public class NewsAndLabelID implements Serializable {
    private String newsID;
    private String labelID;
}
