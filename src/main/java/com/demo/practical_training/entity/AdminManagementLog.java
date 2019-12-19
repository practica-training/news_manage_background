package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

/**
 * 超级管理员管理普通管理员的日志
 */
@Entity
@Data
public class AdminManagementLog extends BaseEntity {

    /**
     * 被超级管理员管理的管理员ID
     */
    @OneToOne
    @JoinColumn(name = "adminid",unique = true, nullable = false)
    private Admin admin;
    /**
     * 处理内容，超级管理员对这个管理员做了什么
     */
    private String operationalContent;
    /**
     * 处理时间
     */
    @Column(columnDefinition="TIMESTAMP")
    private Timestamp processingTime;

}
