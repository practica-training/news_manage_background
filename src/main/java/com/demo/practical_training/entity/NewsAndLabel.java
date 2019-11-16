package com.demo.practical_training.entity;

import com.demo.practical_training.entity.joint_primary_key.NewsAndLabelID;

import javax.persistence.*;

/**
 * 新闻对应标签
 * 弃用
 */
//@Entity
@IdClass(NewsAndLabelID.class)
@Deprecated
public class NewsAndLabel {
    /**
     * 新闻ID
     *    懒加载，级联刷新操作
     *    不可为空，可更新，插入
     */
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "newsID")
    private News news;
    /**
     * 标签ID
     */
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "labelID")
    private NewsLabel newsLabel;



}
