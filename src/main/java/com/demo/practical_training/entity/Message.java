package com.demo.practical_training.entity;

import com.demo.practical_training.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 消息
 */
@Entity
@Data
public class Message extends BaseEntity {

    /**
     * 来源ID
     */
    private String formID;
    /**
     * 去处ID（某个用户）
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "toID")
    private User user;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否已读 0未读/1已读
     */
    private Integer isRead = 0;
    /**
     * 创建时间
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;
}
