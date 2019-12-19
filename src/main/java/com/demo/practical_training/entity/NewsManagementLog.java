package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Entity
/**
 * 管理员的新闻管理日志
 */
@Data
public class NewsManagementLog extends BaseEntity {
    /**
//     * 管理员ID
//     */
//    @OneToOne
//    @JoinColumn(name = "adminid",unique = true, nullable = false)
//    private Admin admin;
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
