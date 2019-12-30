package com.demo.practical_training.manage;

import com.demo.practical_training.dao.UserApplyToNewsMakerRepository;
import com.demo.practical_training.dao.UserRepository;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.entity.UserApplyToNewsMaker;
import com.demo.practical_training.utils.GenerateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserToNewsMakerTest {
    @Autowired
    private UserApplyToNewsMakerRepository userApplyToNewsMakerRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insert() {
        List<User> userList = this.userRepository.findAll();
        for (int i = 0; i < 100; i++) {
            UserApplyToNewsMaker userApplyToNewsMaker = new UserApplyToNewsMaker();
            int index = new Random().nextInt(userList.size());
            userApplyToNewsMaker.setUser(userList.get(index));
            userApplyToNewsMaker.setReason(GenerateUtil.getName()+GenerateUtil.getName());
            this.userApplyToNewsMakerRepository.save(userApplyToNewsMaker);
        }
    }
}
