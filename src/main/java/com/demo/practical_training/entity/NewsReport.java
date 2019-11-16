package com.demo.practical_training.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 新闻举报
 */
@Entity
@Data
public class NewsReport {
    /**
     * 举报ID
     */
    @Id
    private String reportID;
    /**
     * 举报者
     * 懒加载，级联刷新操作
     * 不可为空，不可更新，插入
     */
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "userID",updatable = false,nullable = false,insertable = false)
    private User user;
    /**
     * 被举报的新闻ID
     *      懒加载，级联刷新操作
     *      不可为空，不可更新，插入
     */
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "newsID",updatable = false,nullable = false,insertable = false)
    private News news;
    /**
     * 举报原因
     */
    private String reportReason;
    /**
     * 举报时间
     */
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp reportTime;
    /**
     * 审核状态 0等待审核 1审核完成
     */
    private Integer reviewState;
    /**
     * 是否违规 0没有违规 1违规
     */
    private Integer isIllegal;

}
