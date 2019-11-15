package com.demo.practical_training.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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

    /**
     * 无参构造器
     */
    public Admin() {
    }

    /**
     * 全参构造器
     * @param adminID 管理员ID
     * @param adminAvatar 管理员头像路径
     * @param adminName 管理员用户名
     * @param adminPassword 管理员登陆密码
     * @param adminPermission 管理员权限,0可以管理全部,1仅可以管理用户,2仅可以管理新闻
     */
    public Admin(String adminID, String adminAvatar, String adminName, String adminPassword, Integer adminPermission) {
        this.adminID = adminID;
        this.adminAvatar = adminAvatar;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminPermission = adminPermission;
    }

    /**
     * 获取管理员ID
     * @return
     */
    public String getAdminID() {
        return adminID;
    }

    /**
     * 设置管理员ID
     * @return
     */
    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    /**
     * 获取管管理员头像路径
     * @return
     */
    public String getAdminAvatar() {
        return adminAvatar;
    }

    /**
     * 设置管管理员头像路径
     * @return
     */
    public void setAdminAvatar(String adminAvatar) {
        this.adminAvatar = adminAvatar;
    }

    /**
     * 获取管理员用户名
     * @return
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * 设置管理员用户名
     * @return
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * 获取管理员登陆密码
     * @return
     */
    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     * 设置管理员登陆密码
     * @return
     */
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    /**
     * 获取管理员权限
     * @return
     */
    public Integer getAdminPermission() {
        return adminPermission;
    }

    /**
     * 设置管理员权限 0可以管理全部,1仅可以管理用户,2仅可以管理新闻
     * @return
     */
    public void setAdminPermission(Integer adminPermission) {
        this.adminPermission = adminPermission;
    }
}
