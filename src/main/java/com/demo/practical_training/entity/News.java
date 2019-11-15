package com.demo.practical_training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 新闻
 */
@Entity
public class News {
    /**
     * 新闻ID
     */
    @Id
    @Column(columnDefinition = "CHAR(33)")
    private String newsID;
    /**
     * 新闻封面路径
     */
    private String newsAvatar;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 新闻内容
     */
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    /**
     * 新闻创建时间
     */
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp createTime;
    /**
     * 新闻发布时间
     */
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp publishTime;
    /**
     * 阅读数
     */
    private Long readNumber;
    /**
     * 点赞数
     */
    private Long likeNumber;
    /**
     * 新闻状态 0草稿 1审核中 2审核失败 3等待发布 4已发布 -1已删除 -2违规删除
     */
    private Integer newsState;
    /**
     *新闻权重 0普通新闻 1轮播图新闻
     */
    private Integer newsWeights;
    /**
     * 失败原因（如果审核失败则会有）
     */
    private String failureReason;

    public News() {
    }

    public News(String newsID, String newsAvatar,String title, String content, Timestamp createTime, Timestamp publishTime, Long readNumber, Long likeNumber, Integer newsState, Integer newsWeights, String failureReason) {
        this.newsID = newsID;
        this.newsAvatar = newsAvatar;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.publishTime = publishTime;
        this.readNumber = readNumber;
        this.likeNumber = likeNumber;
        this.newsState = newsState;
        this.newsWeights = newsWeights;
        this.failureReason = failureReason;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getNewsAvatar() {
        return newsAvatar;
    }

    public void setNewsAvatar(String newsAvatar) {
        this.newsAvatar = newsAvatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Long getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(Long readNumber) {
        this.readNumber = readNumber;
    }

    public Long getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Long likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getNewsState() {
        return newsState;
    }

    public void setNewsState(Integer newsState) {
        this.newsState = newsState;
    }

    public Integer getNewsWeights() {
        return newsWeights;
    }

    public void setNewsWeights(Integer newsWeights) {
        this.newsWeights = newsWeights;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
