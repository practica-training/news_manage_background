package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.model.request.QueryNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("/news")
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsRequest queryNewsRequest){
        return newsService.findList(pageRequest,queryNewsRequest);
    }
}
