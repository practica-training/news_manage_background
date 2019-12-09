package com.demo.practical_training.manage;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.NewsReportRepository;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.NewsReport;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.manage.service.NewsReportService;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.manage.service.UserService;
import com.demo.practical_training.model.request.QueryNewsReportRequest;
import com.demo.practical_training.utils.GenerateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewsReportTest {
    @Autowired
    NewsReportService newsReportService;
    @Autowired
    NewsService newsService;
    @Autowired
    UserService userService;
    @Autowired
    NewsReportRepository newsReportRepository;

    /**
     * 测试新增新闻举报
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        for (int i = 0; i < 20; i++) {
            NewsReport newsReport = new NewsReport();
            News news = newsService.findById("8a8180846eafdb1e016eafdc4bd90025");
            User user = userService.findById("4028ab0d6eafe408016eafe43e4c0001");
            newsReport.setIsIllegal(1);
            newsReport.setReviewState(0);
            newsReport.setNews(news);
            newsReport.setUser(user);
            newsReport.setReportReason(GenerateUtil.getName()+GenerateUtil.getName());
            Date date = new Date();
            newsReport.setReportTime(new Timestamp(date.getTime()));
            NewsReport save = newsReportRepository.save(newsReport);
            System.out.println(save);
        }
    }

    /**
    * 测试分页排序条件查询
     */
    @Test
    @Transactional
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
     * 测试根据newsid查询新闻举报
     */
    @Test
    public void testFindByNewsid(){
        List<NewsReport> list = newsReportService.findByNewsid("8a8180846eafdb1e016eafdc359b001f");
        System.out.println(list);
    }
}
