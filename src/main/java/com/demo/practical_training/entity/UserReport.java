package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

/**
 * 用户举报
 */
@Entity
@Data
public class UserReport extends BaseEntity {

    /**
     * 举报者ID
     */
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "userID")
    private User user;
    /**
     * 被举报者ID
     */
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "reportedID")
    private User reported;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "commentID")
    private Comment comment;
    /**
     * 举报原因
     */
    private String reportReason;
    /**
     * 举报时间
     */
    @Column(columnDefinition = "TIMESTAMP default current_timestamp")
    private Timestamp reportTime;

    /**
     * 审核状态 0等待审核 1审核完成
     * 默认是0
     */
    @Column(columnDefinition = "tinyint default 0")
    private Integer reviewState;
    /**
     * 是否违规 0没有违规 1违规
     */
    private Integer isIllegal;


}
