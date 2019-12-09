package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 评论
 */
@Entity
@Data
public class Comment extends BaseEntity {

    /**
     * 新闻ID
     */
    @OneToOne
    @JoinColumn(name = "newsID")
    private News news;
    /**
     * 用户ID
     */
    @OneToOne
    @JoinColumn(name = "userID")
    private User user;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 评论时间
     */
    @Column(columnDefinition="TIMESTAMP")
    private Timestamp commentTime;
    /**
     * 回复用户
     */
    @OneToOne
    @JoinColumn(name = "replyUserId")
    private User replyUser;
    /**
     * 回复用户是否已读,0未读,1已读
     */
    @Column(columnDefinition = "tinyint default 0")
    private Integer replyUserHasRead;
    /**
     * 点赞数
     */
    @Column(columnDefinition = "int default 0")
    private Long likeNumber;
}
