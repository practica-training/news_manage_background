package com.demo.practical_training.manage;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.web.UserPageRequest;
import com.demo.practical_training.dao.UserRepository;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.manage.service.UserService;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


    /**
     * 测试新增用户
     */
    @Test
    public void testAdd(){
        for (int i = 0; i < 15; i++) {
            User user = new User();
            //设置用户名
            user.setUserName(GenerateUtil.getName());
            //设置性别 0女 1男
            user.setUserSex(1);
            //设置用户密码
            user.setUserPassword(GenerateUtil.getRandomNumber(6));
            //设置手机号码
            user.setUserPhone(GenerateUtil.getRandomNumber(11));
            //设置是否实名认证
            user.setIsCertified(0);
            //设置违规次数
            user.setViolationNumber(0);
            //设置用户状态 1正常 0已注销 -1违规禁言 -2违规封号
            user.setUserState(1);
            //设置用户头像路径
            user.setUserAvatar("C:\\Users\\zhong\\Pictures\\Saved Pictures");
            String date1 = "2019-11-25T19:20"; // <input type="datetime-local"> 输入参数
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            try {
                Date dt1 = sdf.parse(date1);
                //设置注册时间
                user.setRegistrationTime(new Timestamp(dt1.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            userService.add(user);
        }

    }

    /**
     * 测试分页排序条件查询
     */
    @Test
    public void testFindList(){
        UserPageRequest userPageRequest = new UserPageRequest();
        QueryResponseResult list = userService.findUserManageList(userPageRequest);
        System.out.println(list);
    }

    /**
     * 测试根据name查询用户
     */
    @Test
    public void testFindByName(){
        List<User> list = userService.findByUserName("唐杰涛");
        System.out.println(list.get(0));
    }

    /**
     * 测试根据name查询用户
     */
    @Test
    public void testFindByPhone(){
        List<User> list = userService.findByUserPhone("111");
        if(null == list || list.size() ==0 ){
            System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        }
    }
    @Test
    public void addNickName(){
        List<User> userList = this.userRepository.findAll();
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).setUserNickname(GenerateUtil.getName());
        }
        this.userRepository.saveAll(userList);
    }
    /**
     * 测试删除全部用户
     */
    @Test
    public void testDeleteAll(){
        userRepository.deleteAll();
    }
}
