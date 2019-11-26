package com.demo.practical_training.entity;

import com.demo.practical_training.common.myEnum.Power;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
     * 管理员权限等级,默认是0
     */
    @Column(columnDefinition = "0")
    private Integer power;
}
