package com.demo.practical_training.entity;

import com.demo.practical_training.entity.joint_primary_key.UserAndLabelID;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * 用户和新闻标签对应表
 */
//@Entity
@IdClass(UserAndLabelID.class)
@Data
@Deprecated
public class UserAndLabel {
    /**
     * 举报者
     * 懒加载，级联刷新操作
     * 不可为空，不可更新，插入
     */
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "userID",updatable = false,nullable = false,insertable = false)
    private User user;

    /**
     * 标签ID
     * 懒加载，级联刷新操作
     * 不可为空，不可更新，插入
     */
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "labelID",updatable = false,nullable = false,insertable = false)
    @Id
    private NewsLabel newsLabel;

}
