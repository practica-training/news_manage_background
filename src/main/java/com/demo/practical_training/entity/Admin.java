package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Admin extends BaseEntity {

    /**
     * 管理员头像路径
     */
    private String adminAvatar;
    /**
     * 管理员用户名
     */
    @Column(unique = true)
    private String adminName;
    /**
     * 管理员登陆密码
     */
    private String adminPassword;
    /**
     * 管理员权限等级,默认是0 有六个权限 0 1 2 3 4
     */
    @Column(columnDefinition = "tinyint default 0")
    private Integer power;
}
