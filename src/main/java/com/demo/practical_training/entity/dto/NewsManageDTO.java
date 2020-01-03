package com.demo.practical_training.entity.dto;

import lombok.Data;

/**
 * 封装管理新闻返回对象
 */
@Data
public class NewsManageDTO {
// *  *           {
// *      *           newsId: "新闻id",
// *                  newsAvatar:"新闻封面路径",
// *      *           newsTitle: "新闻标题",
// *      *           newsState: "是否违规",
// *                  violationReason:"违规原因",
// *      *           content: "新闻内容",
//             *      readNumber:"阅读数",
// *                  likeNumber:"点赞数",
// *                  publishTime:"发布时间"
//                *  *              注意发布时间格式为yyyy-mm-dd
//                *  *          },
    private String newsId;
    private String newsAvatar;
    private Long readNumber;
    private Long likeNumber;
    private Integer newsState;
    private String newsTitle;
    private String violationReason;
    private String content;
    private String publishTime;
    private Integer newsWeights;


    //提供返回结果的业务层

}
