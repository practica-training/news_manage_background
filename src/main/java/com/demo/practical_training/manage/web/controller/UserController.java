package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.common.web.UserPageRequest;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.manage.service.UserService;
import com.demo.practical_training.model.request.QueryUserRequest;
import com.demo.practical_training.model.response.AdminCode;
import com.demo.practical_training.model.response.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制层
 */
@RestController
@RequestMapping("/manage/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 分页排序条件查询用户列表
     * @param pageRequest
     * @param queryUserRequest
     * @return
     */
    @GetMapping("/findList")
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserRequest queryUserRequest){
        return userService.findList(pageRequest,queryUserRequest);
    }

    /**
     * 分页排序条件查询新闻发布者列表
     * @param pageRequest
     * @param queryUserRequest
     * @return
     */
    @GetMapping("/findPublicList")
    public QueryResponseResult findPublicList(STablePageRequest pageRequest, QueryUserRequest queryUserRequest){
        return userService.findPublicList(pageRequest,queryUserRequest);
    }

    /**
     * 分页和排序加动态查询管理用户页面
     * @param pageRequest
     * @return
     */
    @GetMapping("/findUserManageList")
    public QueryResponseResult findUserManageList(UserPageRequest pageRequest){
        return userService.findUserManageList(pageRequest);
    }



    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping
    public UserResult add(@RequestBody User user){
            return userService.add(user);
    }

    /**
     * 更新用户
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/id/{id}")
    public UserResult update(@PathVariable("id") String id, @RequestBody User user){
        return userService.updateById(id,user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return userService.deleteById(id);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public User findOne(@PathVariable("id") String id){
        return userService.findById(id);
    }

    /**
     * 根据手机号查询用户
     * @param userPhone
     * @return
     */
    @GetMapping("/phoneAlong/{userPhone}")
    public ResponseResult phoneAlong(@PathVariable("userPhone") String userPhone){
        List<User> list = userService.findByUserPhone(userPhone);
        if(null == list || list.size() ==0 ){
            return new ResponseResult(AdminCode.USEAPHINE_ALLOW);
        }
        return new ResponseResult(AdminCode.USEAPHINE_NOT_ALLOW);
    }

    /**
     * 根据昵称查询用户
     * @param userNickname
     * @return
     */
    @GetMapping("/nickNameAlong")
    public ResponseResult nickNameAlong(@RequestParam("userNickname") String userNickname){
        List<User> list = userService.findByUserNickname(userNickname);
        if(null == list || list.size() ==0 ){
            return new ResponseResult(AdminCode.USEANICKNAME_ALLOW);
        }
        return new ResponseResult(AdminCode.USEANICKNAME_NOT_ALLOW);
    }
}
