package com.demo.practical_training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 评论
 */
@Entity
public class Comment {
    /**
     * 评论ID
     */
    @Id
    private String commentID;
    /**
     * 新闻ID
     */
    private String newsID;
    /**
     * 用户ID
     */
    private String userID;
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
     * 回复用户ID
     */
    private String replyUserId;
    /**
     * 回复用户是否已读,0未读,1已读
     */
    private Integer replyUserHasRead;
    /**
     * 点赞数
     */
    private Long likeNumber;

    public Comment() {
    }

    public Comment(String commentID, String newsID, String userID, String commentContent, Timestamp commentTime, String replyUserId, Integer replyUserHasRead, Long likeNumber) {
        this.commentID = commentID;
        this.newsID = newsID;
        this.userID = userID;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.replyUserId = replyUserId;
        this.replyUserHasRead = replyUserHasRead;
        this.likeNumber = likeNumber;
    }

    /**
     * 获取评论ID
     * @return
     */
    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Integer getReplyUserHasRead() {
        return replyUserHasRead;
    }

    public void setReplyUserHasRead(Integer replyUserHasRead) {
        this.replyUserHasRead = replyUserHasRead;
    }

    public Long getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Long likeNumber) {
        this.likeNumber = likeNumber;
    }
}
