package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 新闻举报
 */
@Entity
@Data
public class NewsReport extends BaseEntity {

    /**
     * 举报者
     * 懒加载，级联刷新操作
     * 不可为空，不可更新，插入
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "userid")
    private User user;
    /**
     * 被举报的新闻ID
     *      懒加载，级联刷新操作
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "newsid")
    private News news;
    /**
     * 举报原因
     */
    private String reportReason;
    /**
     * 举报时间
     */
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp reportTime;
    /**
     * 审核状态 0等待审核 1审核完成
     */
    private Integer reviewState = 0;
    /**
     * 是否违规 0没有违规 1违规
     */
    private Integer isIllegal;

}
