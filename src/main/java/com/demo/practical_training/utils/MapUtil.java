package com.demo.practical_training.utils;

import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.NewsType;
import com.demo.practical_training.entity.dto.NewsDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class MapUtil {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年 MM月 dd日 HH时 mm分 ss秒");
    public static NewsDTO newsToNewsDTO(News news){
        String id = news.getId();
        String newsTitle = news.getNewsTitle();
        Date publishDate = new Date(news.getPublishTime().getTime());
        String publishDateStr = dateFormat.format(publishDate);
        String content = news.getContent();
        Long readNumber = news.getReadNumber();
        Set<NewsType> newsTypeSet = news.getNewsTypeSet();
        String newsAvatar = news.getNewsAvatar();
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setNewsTypeSet(newsTypeSet);
        newsDTO.setContent(content);
        newsDTO.setCreateTime(publishDateStr);
        newsDTO.setNewsId(id);
        newsDTO.setNewsAvatar(newsAvatar);
        newsDTO.setNewsTitle(newsTitle);
        return newsDTO;
    }
}
