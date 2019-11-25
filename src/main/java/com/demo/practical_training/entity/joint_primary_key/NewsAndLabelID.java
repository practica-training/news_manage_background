package com.demo.practical_training.entity.joint_primary_key;

import java.io.Serializable;

/**
 * 新闻标签和新闻对应表的复合主键
 */
@Deprecated
public class NewsAndLabelID implements Serializable {
    private String newsID;
    private String labelID;
}
