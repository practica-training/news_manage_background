package com.demo.practical_training.manage;

import com.demo.practical_training.dao.CommentRepository;
import com.demo.practical_training.dao.NewsReportRepository;
import com.demo.practical_training.dao.NewsRepository;
import com.demo.practical_training.dao.UserRepository;
import com.demo.practical_training.entity.Comment;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.NewsReport;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.manage.service.UserService;
import com.demo.practical_training.utils.GenerateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Test
    public void insert(){
        List<User> userList = userRepository.findAll();
        List<News> newsList = newsRepository.findAll();
        for (int i = 0; i < 1000; i++) {
            int randomNumber = new Random().nextInt(userList.size());
            User user = userList.get(randomNumber);
            randomNumber = new Random().nextInt(newsList.size());
            News news = newsList.get(randomNumber);
            Comment comment = new Comment();
            comment.setCommentContent(GenerateUtil.getName()+GenerateUtil.getName());
            comment.setCommentTime(new Timestamp(new Date().getTime()));
            comment.setNews(news);
            comment.setUser(user);
            commentRepository.save(comment);
        }

    }
}
