package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.manage.service.NewsManagementLogService;
import com.demo.practical_training.model.request.QueryNewsManagementLogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/newsManagementLog")
public class NewsManagementLogController {
    @Autowired
    NewsManagementLogService newsManagementLogService;

    /**
     * 分页排序条件查询新闻管理日志列表
     * @param pageRequest
     * @param queryNewsManagementLogRequest
     * @return
     */
    @GetMapping("/findList")
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsManagementLogRequest queryNewsManagementLogRequest){
        return newsManagementLogService.findList(pageRequest,queryNewsManagementLogRequest);
    }
}
