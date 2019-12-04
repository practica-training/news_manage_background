package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.UserVerified;
import com.demo.practical_training.manage.service.UserVerifiedService;
import com.demo.practical_training.model.request.QueryUserVerifiedRequest;
import com.demo.practical_training.model.response.UserVerifiedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户实名认证控制层
 */
@RestController
@RequestMapping("/manage/userVerified")
public class UserVerifiedController {
    @Autowired
    UserVerifiedService UserVerifiedService;

    /**
     * 分页排序条件查询用户实名认证列表
     * @param pageRequest
     * @param queryUserVerifiedRequest
     * @return
     */
    @GetMapping
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserVerifiedRequest queryUserVerifiedRequest){
        return UserVerifiedService.findList(pageRequest,queryUserVerifiedRequest);
    }

    /**
     * 新增用户实名认证
     * @param UserVerified
     * @return
     */
    @PostMapping
    public UserVerifiedResult add(@RequestBody UserVerified UserVerified){
            return UserVerifiedService.add(UserVerified);
    }

    /**
     * 更新用户实名认证
     * @param id
     * @param UserVerified
     * @return
     */
    @PutMapping("/id/{id}")
    public UserVerifiedResult update(@PathVariable("id") String id, @RequestBody UserVerified UserVerified){
        return UserVerifiedService.updateById(id,UserVerified);
    }

    /**
     * 删除用户实名认证
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return UserVerifiedService.deleteById(id);
    }

    /**
     * 根据id查询用户实名认证
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public UserVerified findOne(@PathVariable("id") String id){
        return UserVerifiedService.findById(id);
    }
}
