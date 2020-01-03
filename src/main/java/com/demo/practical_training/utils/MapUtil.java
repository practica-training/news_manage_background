package com.demo.practical_training.utils;

import com.demo.practical_training.entity.Comment;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.NewsType;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.entity.dto.CommentDTO;
import com.demo.practical_training.entity.dto.NewsDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class MapUtil {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年 MM月 dd日 HH时 mm分 ss秒");
    public static NewsDTO newsToNewsDTO(News news){
        String id = news.getId();
        String publishDateStr = null;
        String newsTitle = news.getNewsTitle();
        Date createDate = new Date();
        if (news.getCreateTime() != null) {
            createDate = new Date(news.getCreateTime().getTime());
        }
        String createDateStr = dateFormat.format(createDate);
        if(news.getPublishTime()!=null){
            Date publishDate = new Date(news.getPublishTime().getTime());
            publishDateStr = dateFormat.format(publishDate);
        }

        String content = news.getContent();
        Long readNumber = news.getReadNumber();
        Long likeNumber = news.getLikeNumber();
        news.getNewsTypeSet().size();
        Set<NewsType> newsTypeSet = news.getNewsTypeSet();
        String newsAvatar = news.getNewsAvatar();
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setState(news.getNewsState());
        newsDTO.setNewsTypeSet(newsTypeSet);
        newsDTO.setContent(content);
        newsDTO.setPublishTime(publishDateStr);
        newsDTO.setLikeNumber(likeNumber+"");
        newsDTO.setReadNumber(readNumber+"");
        newsDTO.setNewsId(id);
        newsDTO.setCreateTime(createDateStr);
        newsDTO.setNewsAvatar(newsAvatar);
        newsDTO.setNewsTitle(newsTitle);
        return newsDTO;
    }
    public static CommentDTO commentToCommentDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        User user = comment.getUser();
        String userId = user.getId();
        String userNickname = user.getUserNickname();
        String userAvatar = user.getUserAvatar();
        User replyUser = comment.getReplyUser();
        String newsId = comment.getNews().getId();
        Date commentTime = new Date(comment.getCommentTime().getTime());
        String commentTimeStr = dateFormat.format(commentTime);
        Long likeNumber = comment.getLikeNumber();
        String commentContent = comment.getCommentContent();
        String id = comment.getId();
        commentDTO.setNewsId(newsId);
        commentDTO.setUserId(userId);
        commentDTO.setUserNickname(userNickname);
        commentDTO.setUserAvatar(userAvatar);
        commentDTO.setCommentTime(commentTimeStr);
        commentDTO.setId(id);
        if (likeNumber != null) {
            commentDTO.setLikeNumber(likeNumber+"");
        }
        commentDTO.setCommentContent(commentContent);
        if (replyUser != null) {
            String replyUserId = replyUser.getId();
            String replyUserUserAvatar = replyUser.getUserAvatar();
            String replyUserUserNickname = replyUser.getUserNickname();
            commentDTO.setReplyUserAvatar(replyUserUserAvatar);
            commentDTO.setReplyUserId(replyUserId);
            commentDTO.setReplyUserNickname(replyUserUserNickname);
        }
        return commentDTO;
    }
}
