package com.demo.practical_training.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
/**
 * 管理员的新闻管理日志
 */
public class NewsManagementLog {
    /**
     * 日志ID
     */
    @Id
    private String logID;
    /**
     * 管理员ID
     */
    @OneToOne
    @JoinColumn(name = "adminID")
    private Admin admin;
    /**
     * 新闻ID
     */
    @OneToOne
    @JoinColumn(name = "newsID")
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
