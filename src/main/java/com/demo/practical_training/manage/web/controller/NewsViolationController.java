package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.NewsViolation;
import com.demo.practical_training.manage.service.NewsViolationService;
import com.demo.practical_training.model.request.QueryNewsViolationRequest;
import com.demo.practical_training.model.response.NewsViolationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 新闻违规控制层
 */
@RestController
@RequestMapping("/manage/NewsViolation")
public class NewsViolationController {
    @Autowired
    NewsViolationService NewsViolationService;

    /**
     * 分页排序条件查询新闻违规列表
     * @param pageRequest
     * @param queryNewsViolationRequest
     * @return
     */
    @GetMapping
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsViolationRequest queryNewsViolationRequest){
        return NewsViolationService.findList(pageRequest,queryNewsViolationRequest);
    }

    /**
     * 新增新闻违规
     * @param NewsViolation
     * @return
     */
    @PostMapping
    public NewsViolationResult add(@RequestBody NewsViolation NewsViolation){
            return NewsViolationService.add(NewsViolation);
    }

    /**
     * 更新新闻违规
     * @param id
     * @param NewsViolation
     * @return
     */
    @PutMapping("/id/{id}")
    public NewsViolationResult update(@PathVariable("id") String id, @RequestBody NewsViolation NewsViolation){
        return NewsViolationService.updateById(id,NewsViolation);
    }

    /**
     * 删除新闻违规
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return NewsViolationService.deleteById(id);
    }

    /**
     * 根据id查询新闻违规
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public NewsViolation findOne(@PathVariable("id") String id){
        return NewsViolationService.findById(id);
    }
}
