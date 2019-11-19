package com.demo.practical_training.manage;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.manage.dao.NewsRepository;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.model.request.QueryNewsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewsTest {
    @Autowired
    NewsService newsService;

    @Autowired
    NewsRepository newsRepository;

    /**
     * 测试新增新闻
     */
    @Test
    public void testAdd(){
        //创建新闻对象
        for (int i = 0; i < 99; i++){
            News news = new News();
            news.setNewsTitle("王者荣耀");
            news.setContent("阿填"+i+"杀了！");
            ////将String类型格式化为timestamp
            String date1 = "2009-07-16T19:20"; // <input type="datetime-local"> 输入参数
            String date2 = "2009-08-16T19:20";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            try {
                Date dt1 = sdf.parse(date1);
                Date dt2 = sdf.parse(date2);
                news.setCreateTime(new Timestamp(dt1.getTime()));
                news.setPublishTime(new Timestamp(dt2.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            news.setLikeNumber(400L);
            news.setReadNumber(600L);
            news.setNewsState(4);
//        NewsLabel newsLabel = new NewsLabel();
            newsService.add(news);
        }
    }

    /**
     * 测试分页排序条件查询
     */
    @Test
    public void testFindList(){
        STablePageRequest sTablePageRequest = new STablePageRequest();
        QueryNewsRequest queryNewsRequest = new QueryNewsRequest();
        queryNewsRequest.setNewsTitle("英雄");
        QueryResponseResult list = newsService.findList(sTablePageRequest, queryNewsRequest);
        System.out.println(list);
    }

    /**
     * 测试根据id删除新闻
     */
    @Test
    public void testDeleteById(){
        ResponseResult responseResult = newsService.deleteById("4028ab0d6e82bb63016e82bba0170000");
        System.out.println(responseResult);
    }
}
