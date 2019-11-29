package com.demo.practical_training.entity.dto;

import lombok.Data;

import java.util.UUID;

/**
 * 封装新闻举报返回对象
 */
@Data
public class NewsReportDTO{

//    {
//        id:"举报id",
//            newsId:"新闻id",
//            newsTitle:"新闻标题",
//            userName:"举报者",
//            reason:"举报原因",
//            reportTime:"举报时间"
//    },
    private  String id;
    private String newsId;
    private String newsTitle;
    private String userName;
    private String reason;
    private String reportTime;

    //提供返回结果的业务层

    public String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public void setId(String id) {
        this.id = id;
    }
}
