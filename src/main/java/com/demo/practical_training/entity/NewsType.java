package com.demo.practical_training.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "newTypeid", unique = true, nullable = false, length = 20)
    private String newsTypeID;
    /**
     * 名称
     */
    private String name;


}
