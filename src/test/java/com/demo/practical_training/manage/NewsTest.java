package com.demo.practical_training.manage;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.NewsPageRequest;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.NewsRepository;
import com.demo.practical_training.dao.NewsTypeRepository;
import com.demo.practical_training.dao.UserRepository;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.NewsType;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.model.request.QueryNewsRequest;
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

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewsTest {
    @Autowired
    NewsService newsService;
    @Autowired
    NewsTypeRepository newsTypeRepository;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    UserRepository userRepository;

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
    @Transactional
    public void testFindList(){
        STablePageRequest sTablePageRequest = new STablePageRequest();
        QueryNewsRequest queryNewsRequest = new QueryNewsRequest();
        queryNewsRequest.setNewsTitle("王者荣耀");
        QueryResponseResult list = newsService.findList(sTablePageRequest, queryNewsRequest);
        System.out.println(list);
    }

    /**
     * 测试分页排序条件查询
     */
    @Test
    @Transactional
    public void testFindNewsManageList(){
        NewsPageRequest sTablePageRequest = new NewsPageRequest();
        QueryResponseResult list = newsService.findNewsManageList(sTablePageRequest);
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
    @Test
    @Transactional
    public void testfindNewsByNewsType(){
        String id = "8abef8d06f356f9a016f356fcabe0008";
        Pageable pageable = PageRequest.of(0, 10);
        Page<News> newsPage =  newsRepository.findAll(pageable);
        List<NewsType> newsTypeList = this.newsTypeRepository.findAll();
        int index = new Random().nextInt(newsTypeList.size());
        NewsType newsType = newsTypeList.get(index);
        Page<News> newsByNewsType = this.newsRepository.findNewsByNewsType(pageable, id);
        for (News news : newsByNewsType) {
            System.out.println(news);
        }
    }
    @Test
    public void addUserForNews(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> userPage = this.userRepository.findAll(pageable);
        List<News> newsList = this.newsRepository.findAll();
        userPage.getContent().get(0).setNewsList(newsList);
        userPage.forEach(user -> {
            List<News> userNewsList = new ArrayList<>();
            for (int i = 0; i < new Random().nextInt(10); i++) {
                News news = newsList.get(new Random().nextInt(newsList.size()));
                userNewsList.add(news);
            }
        });
        this.userRepository.saveAll(userPage);
    }
    @Test
    public void updateNews(){
        List<News> newsList = this.newsRepository.findAll();
//        List<User> userList = this.userRepository.findAll();
        for (int i = 0; i < newsList.size(); i++) {
            News news = newsList.get(i);
//            news.setUser(userList.get(new Random().nextInt(userList.size())));
//            0草稿 1审核中 2审核失败 3已发布  -1已删除 -2违规 -3下架
            int randomState = new Random().nextInt(7)-3;
            news.setNewsWeights(0);
//            news.setNewsState(randomState);
//            news.setNewsWeights(0);
//            if(randomState==-2){
//                news.setFailureReason(GenerateUtil.getName()+GenerateUtil.getName());
//            }
        }
        for (int i = 0; i < 4; i++) {
            News news = newsList.get(new Random().nextInt(newsList.size()));
            news.setNewsWeights(1);
        }
        this.newsRepository.saveAll(newsList);
    }
    @Test
//    @Transactionanal
    public void testfindUserIdByNewsId(){
        String id = "8a8180846eafdb1e016eafdc31e4001e";
        User user = this.userRepository.findUserByNewsId(id);
//        System.out.println(user);
    }

}
