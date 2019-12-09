package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.manage.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController{
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        HttpSession sessoin = request.getSession();
        String adminname = request.getParameter("adminname");
        String adminPassword = request.getParameter("adminPassword");
        boolean check;
        Admin admin = adminService.findByName(adminname);
        if (admin != null) {
            if (admin.getAdminPassword().equals(adminPassword))
                check = true;
            else
                check = false;
        } else {
            return "用户不存在！";
        }
        if (check) {
            sessoin.setAttribute("adminname", adminname);
            sessoin.setAttribute("adminPassword", adminPassword);
            return "登陆成功！";
        } else {
            return "密码不正确！";
        }
    }
}
