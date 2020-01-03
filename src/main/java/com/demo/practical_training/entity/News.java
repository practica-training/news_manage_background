package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * 新闻
 * 不知道为什么mysql5.7不能同时存在两个timestamp
 * 所以将createTime改为time类型
 */
@Entity
@Data
public class News extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 新闻封面路径
     */
    private String newsAvatar;
    /**
     * 新闻标题
     */
    private String newsTitle;
    /**
     * 新闻内容
     */
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    /**
     * 新闻创建时间
     */

    //默认创建时间
    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Timestamp createTime;
    /**
     * 新闻发布时间
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp publishTime;
    /**
     * 阅读数
     */
    private Long readNumber = 0L;
    /**
     * 点赞数
     */
    private Long likeNumber = 0L;
    /**
     * 新闻状态 0草稿 1审核中 2审核失败 3已发布  -1已删除 -2违规 -3下架
     */
    private Integer newsState = 0;
    /**
     *新闻权重 0普通新闻 1轮播图新闻
     */
    private Integer newsWeights = 0;
    /**
     * 失败原因（如果审核失败则会有）
     */
    private String failureReason;
    /**
     * 新闻的类型，双向多对多
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "newsAndType"
            ,joinColumns = @JoinColumn(name = "newsid",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "news_typeid",referencedColumnName = "id")
    )
    private Set<NewsType> newsTypeSet;
    /**
     * 新闻和标签双向多对多
     * 为将来通过标签查找新闻所用
     */
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "newsAndLabel"
            ,joinColumns = @JoinColumn(name = "newsid",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "news_labelid",referencedColumnName = "id")
    )
    private List<NewsLabel> newsLabelList;

}
