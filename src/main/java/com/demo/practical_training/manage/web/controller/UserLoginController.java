package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.LoginResult;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/manage/user")
public class UserLoginController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public LoginResult login(HttpServletRequest request) {
        HttpSession sessoin = request.getSession();
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        boolean check;
        List<User> list = userService.findByUserName(userName);
        if (list!=null&&list.size()!=0) {
            if (list.get(0).getUserPassword().equals(userPassword))
                check = true;
            else
                check = false;
        } else {
            return new LoginResult(null,10000,"用户名不存在!");
        }
        if (check) {
            sessoin.setAttribute("userName", userName);
            sessoin.setAttribute("userPassword", userPassword);
            return new LoginResult(list.get(0).getId(),10001,"登陆成功");
        } else {
            return new LoginResult(null,10002,"密码错误！");
        }
    }
}
