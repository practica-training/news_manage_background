package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 用户
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME"))
@Data
@ToString
public class User extends BaseEntity {

    /**
     * 用户头像路径
     */
    private String userAvatar;
    /**
     * 用户名(可改，但是唯一)
     */
    @Column(unique = true)
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
     * 用户状态 1正常用户 2新闻发布者 0已注销 -1违规禁言 -2违规封号
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
    /**
     * 用户和新闻标签，双向多对多
     * 为将来做个人推荐使用
     */
    @ManyToMany
    @JoinTable(name = "userAndLabel",
        joinColumns = @JoinColumn(name = "userID",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "news_labelid",referencedColumnName = "id")
    )
    private List<NewsLabel> newsLabelList;
    /**
     * 新闻发布者所写过的新闻，单向
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private List<News> newsList;
}
