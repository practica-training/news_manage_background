package com.demo.practical_training.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 新闻申请发布
 */
@Entity
public class NewsApply {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "newsid", unique = true, nullable = false, length = 20)
    private String ID;
    /**
     * 被举报的新闻ID
     *      懒加载，级联刷新操作
     *      不可为空，不可更新，插入
     */
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "newsID")
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
}
