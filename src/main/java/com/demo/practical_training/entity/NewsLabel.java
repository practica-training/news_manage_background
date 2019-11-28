package com.demo.practical_training.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "newLabelid", unique = true, nullable = false, length = 20)
    private String newLabelID;
    /**
     * 标签名
     */
    private String name;
    /**
     *  标签和新闻的双向多对多映射
     *  为将来标签查找新闻，新闻查找标签所用
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "newsLabelList")
    private List<News> newsList;
    /**
     * 用户和标签的双向多对多映射
     * 为将来的双向查找所用
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "newsLabelList")
    private List<User> users;



}
