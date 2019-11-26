package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Admin;
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
}
