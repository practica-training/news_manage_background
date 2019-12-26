package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.manage.service.AdminService;
import com.demo.practical_training.model.response.AdminCode;
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
@RequestMapping("/manage/admin")
public class AdminRegistController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/adminRegist", method = RequestMethod.POST)
    public ResponseResult login(HttpServletRequest request) {
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装成admin对象
        Admin admin = new Admin();
        try {
            BeanUtils.populate(admin,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        List<Admin> list = adminService.findByName(admin.getAdminName());
        if (list!=null&&list.size()!=0) {
            return new ResponseResult(AdminCode.REGIT_FAIL);
        } else {
            //调用service完成注册
            adminService.add(admin);
            return new ResponseResult(AdminCode.REGIT_SUCCESS);
        }
    }
}
