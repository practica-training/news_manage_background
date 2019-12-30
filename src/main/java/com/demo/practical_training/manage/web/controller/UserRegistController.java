package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.manage.service.UserService;
import com.demo.practical_training.model.response.AdminCode;
import com.demo.practical_training.utils.GenerateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage/user")
public class UserRegistController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/userRegist", method = RequestMethod.POST)
    public ResponseResult login(HttpServletRequest request) {
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装成user对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        List<User> list = userService.findByUserName(user.getUserName());
        List<User> userNickNames = userService.findByUserNickname(user.getUserNickname());
        if (list!=null&&list.size()!=0&&userNickNames!=null&&userNickNames.size()!=0) {
            return new ResponseResult(AdminCode.REGIT_FAIL);
        } else {
            user.setUserNickname(GenerateUtil.getRandomNumber(12));
            //调用service完成注册
            userService.add(user);
            return new ResponseResult(AdminCode.REGIT_SUCCESS);
        }
    }
}
