package com.demo.practical_training.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 用户
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME"))
public class User {
    /**
     * 用户ID
     */
    @Id
    @Column(columnDefinition = "CHAR(33)")
    private String userID;
    /**
     * 用户头像路径
     */
    private String userAvatar;
    /**
     * 用户名(可改，但是唯一)
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 性别 0女 1男
     */
    private Integer userSex;
    /**
     * 手机号码(唯一,一个用户绑定一个手机号码)
     */
    private String userPhone;
    /**
     * 注册时间
     */
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp registrationTime;
    /**
     * 用户状态 1正常 0已注销 -1违规禁言 -2违规封号
     */
    private Integer userState;
    /**
     * 是否实名认证 1是 0否
     */
    private Integer isCertified;
    /**
     * 违规次数
     */
    private Integer violationNumber;

    public User() {
    }

    public User(String userID, String userAvatar, String userName, String userPassword, Integer userSex, String userPhone, Timestamp registrationTime, Integer userState, Integer isCertified, Integer violationNumber) {
        this.userID = userID;
        this.userAvatar = userAvatar;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userSex = userSex;
        this.userPhone = userPhone;
        this.registrationTime = registrationTime;
        this.userState = userState;
        this.isCertified = isCertified;
        this.violationNumber = violationNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Integer getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(Integer isCertified) {
        this.isCertified = isCertified;
    }

    public Integer getViolationNumber() {
        return violationNumber;
    }

    public void setViolationNumber(Integer violationNumber) {
        this.violationNumber = violationNumber;
    }
}
