package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
/**
 * 管理员的新闻管理日志
 */
public class NewsManagementLog extends BaseEntity {
    /**
     * 日志ID
     */
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "logid", unique = true, nullable = false, length = 20)
    private String logID;
    /**
     * 管理员ID
     */
    @OneToOne
    @JoinColumn(name = "adminid",unique = true, nullable = false)
    private Admin admin;
    /**
     * 新闻ID
     */
    @OneToOne
    @JoinColumn(name = "newsid")
    private News news;
    /**
     * 处理内容，管理员对这个新闻做了什么
     */
    private String operationalContent;
    /**
     * 处理时间
     */
    @Column(columnDefinition="TIMESTAMP")
    private Timestamp processingTime;
}
