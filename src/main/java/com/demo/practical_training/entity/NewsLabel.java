
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
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "newsLabelList")
    private List<News> newsList;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "newsLabelList")
    private List<User> users;

}
