package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 实名认证表
 */
@Entity
@Data
public class UserVerified extends BaseEntity {

    /**
     * 用户ID
     * 懒加载，级联刷新操作
     *
     */
//    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "userID")
    private User user;
    /**
     * 身份证号码
     */
    private String idCard;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手持身份证照片
     */
    private String photo;
    /**
     * 审核状态 0等待审核 1通过审核 -1审核失败
     */
    private Integer reviewState = 0;
    /**
     * 失败原因（当审核失败时有）
     */
    private String failureReason;
    /**
     * 审核时间（审核状态改变一次就刷新一次）
     */
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp verifiedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp getVerifiedTime() {
        return verifiedTime;
    }
}
