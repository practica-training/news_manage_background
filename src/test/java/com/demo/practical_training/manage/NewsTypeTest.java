package com.demo.practical_training.manage;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.dao.NewsRepository;
import com.demo.practical_training.dao.NewsTypeRepository;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.NewsType;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.utils.GenerateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewsTypeTest {
    @Autowired
    NewsTypeRepository newsTypeRepository;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsService newsService;
    @Test
    public void insert(){
        for (int i = 0; i < 10; i++) {
            NewsType newsType = new NewsType();
            String name = GenerateUtil.getName();
            newsType.setName(name);
            this.newsTypeRepository.save(newsType);
        }
    }
    @Test
    public void newsAndTypeInsert(){
        List<NewsType> newsTypeList = this.newsTypeRepository.findAll();
        Pageable pageable = PageRequest.of(0, 10);
        Page<News> newsPage =  newsRepository.findAll(pageable);
        List<News> newsList = newsPage.getContent();
        for (int i = 0; i < newsList.size(); i++) {
            News news = newsList.get(i);
            Set<NewsType> newsTypeSet = new HashSet<>();
            int size = newsTypeList.size();
            for (int j = 0; j < new Random().nextInt(size); j++) {
                int index = new Random().nextInt(newsTypeList.size());
                NewsType newsType = newsTypeList.get(index);
                newsTypeSet.add(newsType);
            }
            news.setNewsTypeSet(newsTypeSet);
        }
        this.newsRepository.saveAll(newsList);
    }
    @Test
    public void testFindNewsByKind(){
        String id = "8abef8d06f356f9a016f356fcabe0008";
        QueryResponseResult newsByKindId = this.newsService.getNewsByKindId(id, 1);
        List<News> list = newsByKindId.getQueryResult().getList();
        for (News o : list) {
            System.out.println(list);
        }
    }
}
