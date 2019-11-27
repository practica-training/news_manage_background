package com.demo.practical_training.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 新闻违规表
 */
@Entity
@Data
public class NewsViolation {
    /**
     * 违规ID
     */
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "violationid")
    private String violationID;
    /**
     * 被举报的新闻ID
     * 懒加载，级联刷新操作
     * 不可为空，不可更新，插入
     */
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "newsid")
    private News news;
    /**
     * 违规原因
     */
    private String violationReason;
    /**
     * 审核时间
     */
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp reviewTime;

}
