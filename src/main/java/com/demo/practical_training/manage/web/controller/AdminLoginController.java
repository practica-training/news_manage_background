package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.LoginResult;
import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.manage.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminLoginController{
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public LoginResult login(HttpServletRequest request) {
        HttpSession sessoin = request.getSession();
        String adminname = request.getParameter("adminname");
        String adminPassword = request.getParameter("adminPassword");
        boolean check;
        List<Admin> list = adminService.findByName(adminname);
        if (list!=null&&list.size()!=0) {
            if (list.get(0).getAdminPassword().equals(adminPassword))
                check = true;
            else
                check = false;
        } else {
            return new LoginResult(null,10000,"用户名不存在!","false");
        }
        if (check) {
            sessoin.setAttribute("adminname", adminname);
            sessoin.setAttribute("adminPassword", adminPassword);
            return new LoginResult(list.get(0).getId(),10001,"登陆成功","true");
        } else {
            return new LoginResult(null,10002,"密码错误！","false");
        }
    }
}
