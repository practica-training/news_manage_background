package com.demo.practical_training.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Admin {
    /**
     * 管理员ID
     */
    @Id
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
     * 管理员权限,0可以管理全部,1仅可以管理用户,2仅可以管理新闻
     */
    private Integer adminPermission;
}
