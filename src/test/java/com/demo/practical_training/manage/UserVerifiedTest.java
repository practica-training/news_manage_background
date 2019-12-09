package com.demo.practical_training.manage;

import com.demo.practical_training.dao.CommentRepository;
import com.demo.practical_training.dao.UserRepository;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.entity.UserVerified;
import com.demo.practical_training.manage.service.UserVerifiedService;
import com.demo.practical_training.utils.GenerateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserVerifiedTest {
    @Autowired
    UserVerifiedService userVerifiedService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    /**
     * 测试新增实名认证
     */
    @Test
    public void testAdd(){
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < 50; i++) {
            int randomNumber = new Random().nextInt(userList.size());
            User user = userList.get(randomNumber);
            UserVerified userVerified = new UserVerified();
            //设置用户ID
            userVerified.setUser(user);
            //设置真实姓名
            userVerified.setRealName(GenerateUtil.getName());
            //设置身份证号码
            userVerified.setIdCard(GenerateUtil.getRandomNumber(18));
            //设置手持身份证照片
            userVerified.setPhoto("D:\\ideaProject\\image"+GenerateUtil.getRandomNumber(3));
            //设置 审核状态 0等待审核 1通过审核 -1审核失败
            if(i%2==0){
                userVerified.setReviewState(1);
                //设置失败原因（当审核失败时有）
                userVerified.setFailureReason(GenerateUtil.getName());
            }else {
                userVerified.setReviewState(0);
                //设置失败原因（当审核失败时有）
                userVerified.setFailureReason(null);
            }
            String date1 = "2019-11-25T19:20"; // <input type="datetime-local"> 输入参数
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            try {
                Date dt1 = sdf.parse(date1);
                //设置注册时间
                userVerified.setVerifiedTime(new Timestamp(dt1.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            userVerifiedService.add(userVerified);
        }

    }

    /**
     * 测试分页排序条件查询
     */
    @Test
    public void testFindList(){

    }

    /**
     * 测试根据id删除用户
     */
    @Test
    public void testDeleteById(){

    }

}
