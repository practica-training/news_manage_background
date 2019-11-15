package com.demo.practical_training.entity.joint_primary_key;

import java.io.Serializable;

/**
 * 新闻和类型对应的复合主键
 */
public class NewsAndTypeID implements Serializable {
    private String newsID;
    private String typeID;
}