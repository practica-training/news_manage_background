package com.demo.practical_training.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 新闻标签
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
@Data
public class NewsLabel {
    /**
     * 标签ID
     */
    @Id
    private String newLabelID;
    /**
     * 标签名
     */
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "newsLabelList")
    private List<News> newsList;
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "newsLabelList")
    private List<User> users;



}
