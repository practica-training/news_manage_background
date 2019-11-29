package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @OneToOne
    @JoinColumn(name = "userID")
    private User user;
    /**
     * 被举报者ID
     */
    @OneToOne
    @JoinColumn(name = "reportedID")
    private User reported;
    /**
     * 举报原因
     */
    private String reportReason;
    /**
     * 所举报的评论
     */
    @OneToOne
    @JoinColumn(name = "commentID")
    private Comment comment;
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
