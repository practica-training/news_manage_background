package com.demo.practical_training.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 新闻类别
 */
@Entity
@Data
public class NewsType {
    /**
     * 类别ID
     */
    @Id
    private String newsTypeID;
    /**
     * 名称
     */
    private String name;


}
