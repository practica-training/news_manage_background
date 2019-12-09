package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.UserReport;
import com.demo.practical_training.manage.service.UserReportService;
import com.demo.practical_training.model.request.QueryUserReportRequest;
import com.demo.practical_training.model.response.UserReportResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户举报控制层
 */
@RestController
@RequestMapping("/manage/userReport")
public class UserReportController {
    @Autowired
    UserReportService UserReportService;

    /**
     * 分页排序条件查询用户举报列表
     * @param pageRequest
     * @param queryUserReportRequest
     * @return
     */
    @GetMapping
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserReportRequest queryUserReportRequest){
        return UserReportService.findList(pageRequest,queryUserReportRequest);
    }

    /**
     * 新增用户举报
     * @param UserReport
     * @return
     */
    @PostMapping
    public UserReportResult add(@RequestBody UserReport UserReport){
            return UserReportService.add(UserReport);
    }

    /**
     * 更新用户举报
     * @param id
     * @param userReport
     * @return
     */
    @PutMapping("/id/{id}")
    public UserReportResult update(@PathVariable("id") String id, @RequestBody UserReport userReport){

        return UserReportService.updateById(id,userReport);
    }

    /**
     * 删除用户举报
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return UserReportService.deleteById(id);
    }

    /**
     * 根据id查询用户举报
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public UserReport findOne(@PathVariable("id") String id){
        return UserReportService.findById(id);
    }
}
