package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.NewsReport;
import com.demo.practical_training.manage.service.NewsReportService;
import com.demo.practical_training.model.request.QueryNewsReportRequest;
import com.demo.practical_training.model.response.NewsReportResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 新闻举报控制层
 */
@RestController
@RequestMapping("/manage/newsReport")
public class NewsReportController {
    @Autowired
    NewsReportService NewsReportService;

    /**
     * 分页排序条件查询新闻举报列表
     * @param pageRequest
     * @param queryNewsReportRequest
     * @return
     */
    @GetMapping
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsReportRequest queryNewsReportRequest){
        System.out.println(pageRequest);
        return NewsReportService.findList(pageRequest,queryNewsReportRequest);
    }

    /**
     * 新增新闻举报
     * @param NewsReport
     * @return
     */
    @PostMapping
    public NewsReportResult add(@RequestBody NewsReport NewsReport){
            return NewsReportService.add(NewsReport);
    }

    /**
     * 更新新闻举报
     * @param id
     * @param NewsReport
     * @return
     */
    @PutMapping("/id/{id}")
    public NewsReportResult update(@PathVariable("id") String id, @RequestBody NewsReport NewsReport){
        return NewsReportService.updateById(id,NewsReport);
    }

    /**
     * 删除新闻举报
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return NewsReportService.deleteById(id);
    }

    /**
     * 根据id查询新闻举报
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public NewsReport findOne(@PathVariable("id") String id){
        return NewsReportService.findById(id);
    }
}
