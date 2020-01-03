package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.UserApplyToNewsMaker;
import com.demo.practical_training.manage.service.UserApplyToNewsMakerService;
import com.demo.practical_training.model.request.QueryUserApplyToNewsMakerRequest;
import com.demo.practical_training.model.response.UserApplyToNewsMakerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 申请成为新闻发布者控制层
 */
@RestController
@RequestMapping("/manage/UserApplyToNewsMaker")
public class UserApplyToNewsMakerController {
    @Autowired
    UserApplyToNewsMakerService UserApplyToNewsMakerService;

    /**
     * 分页排序条件查询申请成为新闻发布者列表
     * @param pageRequest
     * @param queryUserApplyToNewsMakerRequest
     * @return
     */
    @GetMapping
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserApplyToNewsMakerRequest queryUserApplyToNewsMakerRequest){
        return UserApplyToNewsMakerService.findList(pageRequest,queryUserApplyToNewsMakerRequest);
    }

    /**
     * 新增申请成为新闻发布者
     * @param UserApplyToNewsMaker
     * @return
     */
    @PostMapping
    public UserApplyToNewsMakerResult add(@RequestBody UserApplyToNewsMaker UserApplyToNewsMaker){
        return UserApplyToNewsMakerService.add(UserApplyToNewsMaker);
    }

    /**
     * 更新申请成为新闻发布者
     * @param id
     * @param UserApplyToNewsMaker
     * @return
     */
    @PutMapping("/id/{id}")
    public UserApplyToNewsMakerResult update(@PathVariable("id") String id, @RequestBody UserApplyToNewsMaker UserApplyToNewsMaker){
        return UserApplyToNewsMakerService.updateById(id,UserApplyToNewsMaker);
    }

    /**
     * 删除申请成为新闻发布者
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return UserApplyToNewsMakerService.deleteById(id);
    }

    /**
     * 根据id查询申请成为新闻发布者
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public UserApplyToNewsMaker findOne(@PathVariable("id") String id){
        return UserApplyToNewsMakerService.findById(id);
    }
}
