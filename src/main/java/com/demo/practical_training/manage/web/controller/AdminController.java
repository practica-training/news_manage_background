package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.entity.dto.NewsOffDTO;
import com.demo.practical_training.entity.dto.ReasonDTO;
import com.demo.practical_training.entity.dto.UserOffDTO;
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
     * 分页排序条件查询管理管理员列表
     * @param pageRequest
     * @param queryAdminRequest
     * @return
     */
    @GetMapping("/findManageList")
    public QueryResponseResult findManageList(STablePageRequest pageRequest, QueryAdminRequest queryAdminRequest){
        pageRequest.setSortField("power");
        pageRequest.setSortNews("descend");
        return adminService.findManageList(pageRequest,queryAdminRequest);
    }

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



    /**
     * 对新闻下架处理
     * @param newsOffDTO
     * @return
     */
    @PutMapping("/reviewNewsOff")
    public ResponseResult reviewNewsOff(@RequestBody NewsOffDTO newsOffDTO){
        return adminService.reviewNewsOff(newsOffDTO.getId(),newsOffDTO.getFailureReason());
    }

    /**
     * 对新闻进行解除下架处理
     * @param id
     * @return
     */
    @PutMapping("/reviewNewsOn/{id}")
    public ResponseResult reviewNewsOn(@PathVariable("id") String id){

        return adminService.reviewNewsOn(id);
    }

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
     *忽略新闻举报
     * @param id
     * @param news
     * @return
     */
    @PutMapping("/reviewNewsMiss/{id}")
    public ResponseResult reviewNewsMiss(@PathVariable("id") String id){
        return adminService.reviewNewsMiss(id);
    }


    /*
     *审核用户举报  惩罚用户
     * @param id
     * @param news
     * @return
     */
    @PutMapping("/reviewUser")
    public ResponseResult reviewUser(@RequestBody UserOffDTO userOffDTO){
        return adminService.reviewUser(userOffDTO.getId(),userOffDTO.getNormalDate());
    }


    /*
     *忽略用户举报
     * @param id
     * @return
     */
    @PutMapping("/reviewUserMiss/{id}")
    public ResponseResult reviewUserMiss(@PathVariable("id") String id){
        return adminService.reviewUserMiss(id);
    }

    /**
     * 对用户进行禁言操作
     * @param userOffDTO
     * @return
     */
    @PutMapping("/reviewUserOff")
    public ResponseResult reviewUserOff(@RequestBody UserOffDTO userOffDTO){
        return adminService.reviewUserOff(userOffDTO.getId(),userOffDTO.getFailureReason(),userOffDTO.getNormalDate());
    }

    /**
     * 对用户进行解除禁言操作
     * @param id
     * @return
     */
    @PutMapping("/reviewUserOn/{id}")
    public ResponseResult reviewUserOn(@PathVariable("id") String id){
        return adminService.reviewUserOn(id);
    }

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
     *审核用户申请为新闻发布者不通过
     * @param reasonDTO
     * @return
     */
    @PutMapping("/reviewUserBecomePublishOn")
    public ResponseResult reviewUserBecomePublishOff(@RequestBody ReasonDTO reasonDTO){
        return adminService.reviewUserBecomePublishOff(reasonDTO.getId(),reasonDTO.getFailureReason());
    }


    /**
     *审核用户申请为新闻发布者通过
     * @param id
     * @return
     */
    @PutMapping("/reviewUserBecomePublishOn/{id}")
    public ResponseResult reviewUserBecomePublishOn(@PathVariable("id") String id){
        return adminService.reviewUserBecomePublishOn(id);
    }

    /**
     *管理员新闻发布者(将新闻发布者进行降级）
     * @param id
     * @return
     */
    @PutMapping("/reviewUserBecomeUser/{id}")
    public ResponseResult reviewUserBecomeUser(@PathVariable("id") String id){
        return adminService.reviewUserBecomeUser(id);
    }

    /**
     *管理管理员
     * @param id
     * @param power
     * @return
     */
    @PutMapping("/managementAdmin/{id}/{power}")
    public ResponseResult managementAdmin(@PathVariable("id") String id,@PathVariable("power") Integer power){
        if ("1".equals(id)) {
            return new ResponseResult(CommonCode.FAIL);
        }
        return adminService.ManagementAdmin(id,power);
    }
}
