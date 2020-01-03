package com.demo.practical_training.entity.dto;

import com.demo.practical_training.entity.NewsType;
import lombok.Data;

import java.util.Set;

/**
 * 封装新闻返回对象
 */
@Data
public class NewsDTO {

//    {
// *           newsId: "新闻id",
// *           newsTitle: "新闻标题",
// *           content: "新闻内容",
// *           newsTypeList: ["类型1", "类型2"],
// *           newsAvatar: "新闻封面",
// *           createTime:"创建时间"
//            *         }
    private String newsId;
    private String newsTitle;
    private String content;
    private Set<NewsType> newsTypeSet;
    private String newsAvatar;
    private String publishTime;
    private String likeNumber = "0";
    private String readNumber = "0";
    private String createTime;
    private Integer  state = 0;
    //提供返回结果的业务层

}
