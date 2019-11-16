package com.demo.practical_training.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 新闻
 */
@Entity
@Data
public class News {
    /**
     * 新闻ID
     */
    @Id
    @Column(columnDefinition = "CHAR(33)")
    private String newsID;
    @ManyToMany
    @JoinTable(name = "newsAndLabel"
            ,joinColumns = @JoinColumn(name = "newsID",referencedColumnName = "newsID"),
            inverseJoinColumns = @JoinColumn(name = "newLabelID",referencedColumnName = "newLabelID")
            )
    private List<NewsLabel> newsLabelList;
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
    @ManyToMany
    @JoinTable(name = "newsAndType"
            ,joinColumns = @JoinColumn(name = "newsID",referencedColumnName = "newsID"),
            inverseJoinColumns = @JoinColumn(name = "newsTypeID",referencedColumnName = "newsTypeID")
    )
    private List<NewsType> newsTypeList;

}
