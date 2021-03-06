package com.demo.practical_training.manage;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.dao.AdminRepository;
import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.manage.service.AdminService;
import com.demo.practical_training.manage.service.NewsReportService;
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
public class AdminTest {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    NewsReportService newsReportService;
    @Test
    public void updataPower(){
        List<Admin> adminList = this.adminRepository.findAll();
        for (int i = 0; i < adminList.size(); i++) {
            int power = new Random().nextInt(5);
            adminList.get(i).setPower(power);
        }
        this.adminRepository.saveAll(adminList);
    }
    /**
     * 测试新增管理员
     */
    @Test
    public void testAdd(){
        for (int i = 0; i < 15; i++) {
            Admin admin = new Admin();
            //设置管理员用户名
            admin.setAdminName(GenerateUtil.getName());
            //设置管理员登陆密码
            admin.setAdminPassword(GenerateUtil.getRandomNumber(6));
            admin.setPower(new Random().nextInt(4));
            //设置管理员头像路径
            admin.setAdminAvatar("15780520444500.jpg");
            //设置管理员权限等级
            adminService.add(admin);
        }
    }

    /**
     * 测试审核新闻举报
     */
    @Test
    public void testReviewNews(){
        ResponseResult responseResult = adminService.reviewNews("8a8180846eafdb1e016eafdc394f0020");
        System.out.println(responseResult);
    }
}
