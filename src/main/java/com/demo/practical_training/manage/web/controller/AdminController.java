package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.manage.service.AdminService;
import com.demo.practical_training.model.request.QueryAdminRequest;
import com.demo.practical_training.model.response.AdminResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员控制层
 */
@RestController
@RequestMapping("/manage/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    /**
     * 分页排序条件查询管理员列表
     * @param pageRequest
     * @param queryAdminRequest
     * @return
     */
    @GetMapping
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryAdminRequest queryAdminRequest){
        return adminService.findList(pageRequest,queryAdminRequest);
    }

    /**
     * 新增管理员
     * @param admin
     * @return
     */
    @PostMapping
    public AdminResult add(@RequestBody Admin admin){
            return adminService.add(admin);
    }

    /**
     * 更新管理员
     * @param id
     * @param admin
     * @return
     */
    @PutMapping("/id/{id}")
    public AdminResult update(@PathVariable("id") String id, @RequestBody Admin admin){
        return adminService.updateById(id,admin);
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return adminService.deleteById(id);
    }

    /**
     * 根据id查询管理员
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public Admin findOne(@PathVariable("id") String id){
        return adminService.findById(id);
    }

    /**
     * 审核新闻发布
     * @param id
     * @param news
     * @return
     */
    @PutMapping("/reviewNewsPublish/{id}")
    public ResponseResult reviewNewsPublish(@PathVariable("id") String id,@RequestBody News news){
        return adminService.reviewNewsPublish(id,news);
    }

//    /**
//     *对新闻下架处理
//     * @param id
//     * @param news
//     * @return
//     */
//    @PutMapping("/reviewNewsOff/{id}")
//    public ResponseResult reviewNewsOff(@PathVariable("id") String id,@RequestBody News news){
//        return adminService.reviewNewsOff(id,news);
//    }

    /*
     *审核新闻举报
     * @param id
     * @param news
     * @return
     */
    @PutMapping("/reviewNews/{id}")
    public ResponseResult reviewNews(@PathVariable("id") String id){
        return adminService.reviewNews(id);
    }

    /*
     *审核用户举报
     * @param id
     * @param news
     * @return
     */
    @PutMapping("/reviewUser/{id}")
    public ResponseResult reviewUser(@PathVariable("id") String id){
        return adminService.reviewUser(id);
    }

    /**
     *对用户封号处理
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/reviewUserOff/{id}")
    public ResponseResult reviewUserOff(@PathVariable("id") String id,@RequestBody User user){
        return adminService.reviewUserOff(id,user);
    }

    /**
     *对用户实名认证处理
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/reviewUserVerified/{id}")
    public ResponseResult reviewUserVerified(@PathVariable("id") String id,@RequestBody User user){
        return adminService.reviewUserVerified(id,user);
    }

    /**
     *审核用户申请为新闻发布者
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/reviewUserBecomePublish/{id}")
    public ResponseResult reviewUserBecomePublish(@PathVariable("id") String id,@RequestBody User user){
        return adminService.reviewUserBecomePublish(id,user);
    }

    /**
     *管理员新闻发布者(将新闻发布者进行降级）
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/reviewUserBecomeUser/{id}")
    public ResponseResult reviewUserBecomeUser(@PathVariable("id") String id,@RequestBody User user){
        return adminService.reviewUserBecomeUser(id,user);
    }

    /**
     *管理管理员
     * @param id
     * @param admin
     * @return
     */
    @PutMapping("/ManagementAdmin/{id}")
    public ResponseResult ManagementAdmin(@PathVariable("id") String id,@RequestBody Admin admin){
        return adminService.ManagementAdmin(id,admin);
    }
}
