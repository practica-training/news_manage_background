package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 管理员的用户管理日志
 */
@Entity
@Data
public class UserManagementLog extends BaseEntity {

    /**
     * 管理ID
     */
    @JoinColumn(name = "adminID")
    @OneToOne
    private Admin admin;
    /**
     * 用户ID
     */
    @JoinColumn(name = "userID")
    @OneToOne
    private User user;
    /**
     * 处理内容，管理员对这个用户做了什么
     */
    private String operationalContent;
    /**
     * 处理时间
     */
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp processingTime;
}
