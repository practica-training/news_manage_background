package com.demo.practical_training.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Admin {
    /**
     * 管理员ID
     */
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "adminid", unique = true, nullable = false, length = 20)
    private String adminID;
    /**
     * 管理员头像路径
     */
    private String adminAvatar;
    /**
     * 管理员用户名
     */
    private String adminName;
    /**
     * 管理员登陆密码
     */
    private String adminPassword;
    /**
     * 管理员权限等级
     */
    private Integer power;
}
