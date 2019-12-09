package com.demo.practical_training.manage;

import com.demo.practical_training.dao.CommentRepository;
import com.demo.practical_training.dao.NewsRepository;
import com.demo.practical_training.dao.UserReportRepository;
import com.demo.practical_training.dao.UserRepository;
import com.demo.practical_training.entity.Comment;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.entity.UserReport;
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
public class UserReportTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UserReportRepository userReportRepository;
    @Test
    public void insert(){
        List<User> userList = userRepository.findAll();
        List<Comment> commentList = commentRepository.findAll();
        for (int i = 0; i < 100; i++) {
            int randomNumber = new Random().nextInt(userList.size());
            User user = userList.get(randomNumber);
            randomNumber = new Random().nextInt(userList.size());
            User reportedUser = userList.get(randomNumber);
            randomNumber = new Random().nextInt(commentList.size());
            Comment comment = commentList.get(randomNumber);
            UserReport userReport = new UserReport();
            userReport.setComment(comment);
            userReport.setReported(reportedUser);
            userReport.setReportReason(GenerateUtil.getName()+GenerateUtil.getName());
            userReport.setUser(user);
            userReport.setReviewState(0);
            this.userReportRepository.save(userReport);
        }
    }
    @Test
    public  void  removeAll(){
        this.commentRepository.deleteAll();
    }
}
