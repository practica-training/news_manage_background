package com.demo.practical_training.manage;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.NewsReport;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.manage.service.NewsReportService;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.manage.service.UserService;
import com.demo.practical_training.model.request.QueryNewsReportRequest;
import com.demo.practical_training.model.request.QueryNewsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewsReportTest {
    @Autowired
    NewsReportService newsReportService;
    @Autowired
    NewsService newsService;
    @Autowired
    UserService userService;

    /**
     * 测试新增新闻举报
     */
    @Test
    public void testAdd(){
        NewsReport newsReport = new NewsReport();
        News news = newsService.findById("4028ab0d6e82c2fa016e82c327270001");
        User user = userService.findById("4028ab0d6ea6e6fa016ea6e731690000");
        newsReport.setIsIllegal(1);
        newsReport.setReviewState(1);
        newsReport.setNews(news);
        newsReport.setUser(user);
        newsReport.setReportReason("暴力新闻");
        Date date = new Date();
        newsReport.setReportTime(new Timestamp(date.getTime()));
        newsReportService.add(newsReport);
    }

    /**
    * 测试分页排序条件查询
     */
    @Test
    public void testFindList(){
        STablePageRequest sTablePageRequest = new STablePageRequest();
        QueryNewsReportRequest queryNewsRequest = new QueryNewsReportRequest();
        QueryResponseResult list = newsReportService.findList(sTablePageRequest, queryNewsRequest);
        System.out.println(list);
    }

    /**
     * 测试根据id删除用户
     */
    @Test
    public void testDeleteById(){

    }

    /**
     * 测试删除全部用户
     */
    @Test
    public void testDeleteAll(){
    }
}
