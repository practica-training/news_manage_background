package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.entity.dto.ReasonDTO;
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
     *新闻发布审核成功
     * @param id
     * @return
     */
    @PutMapping("/reviewNewsPublishOn/{id}")
    public ResponseResult reviewNewsPublishOn(@PathVariable("id") String id){
        return adminService.reviewNewsPublishOn(id);
    }

    /**
     *新闻发布审核失败
     * @param reasonDTO
     * @return
     */
    @PutMapping("/reviewNewsPublishOff")
    public ResponseResult reviewNewsPublishOff(@RequestBody ReasonDTO reasonDTO) {
        System.out.println(reasonDTO);
        return adminService.reviewNewsPublishOff(reasonDTO.getId(),reasonDTO.getFailureReason());
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
     *审核新闻举报 通过
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
    @PutMapping("/reviewUser")
    public ResponseResult reviewUser(@RequestBody User user){
        System.out.println(user);
        return adminService.reviewUser(user);
    }

//    /**
//     *对用户封号处理
//     * @param id
//     * @param user
//     * @return
//     */
//    @PutMapping("/reviewUserOff/{id}")
//    public ResponseResult reviewUserOff(@PathVariable("id") String id,@RequestBody User user){
//        return adminService.reviewUserOff(id,user);
//    }

    /**
     *对用户实名认证处理通过
     * @param id
     * @return
     */
    @PutMapping("/reviewUserVerifiedOn/{id}")
    public ResponseResult reviewUserVerifiedOn(@PathVariable("id") String id){
        return adminService.reviewUserVerifiedOn(id);
    }

    /**
     *对用户实名认证处理不通过
     * @param reasonDTO
     * @return
     */
    @PutMapping("/reviewUserVerifiedOff")
    public ResponseResult reviewUserVerifiedOff(@RequestBody ReasonDTO reasonDTO){
        System.out.println(reasonDTO);
        return adminService.reviewUserVerifiedOff(reasonDTO.getId(),reasonDTO.getFailureReason());
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
