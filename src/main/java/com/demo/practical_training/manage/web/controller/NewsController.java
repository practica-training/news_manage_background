package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.model.request.QueryNewsRequest;
import com.demo.practical_training.model.response.NewsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *新闻管理
 */
@RestController
@RequestMapping("/manage/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    /**
     * 分页排序条件查询新闻列表
     * @param pageRequest
     * @param queryNewsRequest
     * @return
     */
    @GetMapping("/list")
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsRequest queryNewsRequest){
        return newsService.findList(pageRequest,queryNewsRequest);
    }

    /**
     * 新增新闻
     * @param news
     * @return
     */
    @PostMapping("/add")
    public NewsResult add(@RequestBody News news){
        return newsService.add(news);
    }

    /**
     * 更新新闻
     * @param id
     * @param news
     * @return
     */
    @PutMapping("/update/{id}")
    public NewsResult update(@PathVariable("id") String id,@RequestBody News news){
        return newsService.updateById(id,news);
    }

    /**
     * 删除新闻
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return newsService.deleteById(id);
    }

    /**
     * 根据id查询新闻
     * @param id
     * @return
     */
    @GetMapping("/findOne/{id}")
    public News findOne(@PathVariable("id") String id){
        return newsService.findById(id);
    }
}
