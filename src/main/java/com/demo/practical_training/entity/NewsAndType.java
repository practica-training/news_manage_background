package com.demo.practical_training.entity;


import com.demo.practical_training.entity.joint_primary_key.NewsAndTypeID;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * 新闻和类型对应的表
 */
//@Entity
@IdClass(NewsAndTypeID.class)
@Data
@Deprecated
public class NewsAndType {
    /**
     * 新闻ID
     */
    @Id
//    private News news;
    private String newsID;
    /**
     * 标签ID
     */
    @Id
    private String typeID;

}
