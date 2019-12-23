package com.demo.practical_training.manage;

import com.demo.practical_training.dao.AdminRepository;
import com.demo.practical_training.dao.UserRepository;
import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.entity.Message;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.manage.service.MessageService;
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
public class MessageTest {
    @Autowired
    MessageService messageService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;

    /**
     * 测试根据name查询用户
     */
    @Test
    public void testFindByName(){
        List<Message> list = messageService.findByUserId("8a8180846ecfce25016ecfce91e80008");
        System.out.println(list);
    }
    @Test
    public void insert() {
        List<User> userList = userRepository.findAll();
        List<Admin> adminList = adminRepository.findAll();
        for (int i = 0; i < 100; i++) {
            int randomNumber = new Random().nextInt(userList.size());
            User user = userList.get(randomNumber);
            randomNumber = new Random().nextInt(userList.size());
            Admin admin = adminList.get(randomNumber);
            Message message = new Message();
            message.setContent(GenerateUtil.getName()+GenerateUtil.getName());
            message.setCreateTime(new Timestamp(new Date().getTime()));
            if(i%2==0){
                message.setIsRead(1);
            }else {
                message.setIsRead(0);
            }
            message.setFormID(admin.getId());
            message.setUser(user);
            messageService.add(message);
        }
    }

}
