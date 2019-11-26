package com.demo.practical_training.manage;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.entity.NewsReport;
import com.demo.practical_training.manage.service.AdminService;
import com.demo.practical_training.manage.service.NewsReportService;
import com.demo.practical_training.utils.GenerateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminTest {
    @Autowired
    AdminService adminService;
    @Autowired
    NewsReportService newsReportService;

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
            //设置管理员头像路径
            admin.setAdminAvatar("F:\\javaee");
            //设置管理员权限等级
            admin.setPower(4);
            adminService.add(admin);
        }
    }

    /**
     * 测试审核新闻举报
     */
    @Test
    public void testReviewNews(){
        NewsReport report = newsReportService.findById("4028ab0d6ea6f703016ea6f7351a0000");
        ResponseResult responseResult = adminService.reviewNews(report.getReportID());
        System.out.println(responseResult);
    }
}
