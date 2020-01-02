package com.demo.practical_training.entity.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private String id;
    private String newsId;
    private String userId;
    private String userNickname;
    private String userAvatar;
    private String commentTime;
    private String replyUserId;
    private String replyUserNickname;
    private String replyUserAvatar;
    private String likeNumber = "0";
    private String commentContent;
}
