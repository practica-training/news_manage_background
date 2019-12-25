package com.demo.practical_training.manage.web.controller;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.NewsPageRequest;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.model.request.QueryNewsRequest;
import com.demo.practical_training.model.response.NewsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *新闻控制层
 */
@RestController
@RequestMapping("/manage/news")
public class NewsController {
    @Autowired
    NewsService newsService;
    @GetMapping("/comment/{newsid}/{page}")
    public QueryResponseResult getNewsCommentList(@PathVariable("newsId")String newsId,@PathVariable("page") Integer page){
        return this.newsService.getNewsCommentList(newsId,page);
    }
    /**
     * 获得所有新闻类型
     * @return
     */
    @GetMapping("/kind")
    public QueryResponseResult getNewsKinds(){
        return newsService.getNewsKinds();
    }

    /**
     * 根据新闻类别id分页查询新闻
     * @return
     */
    @GetMapping("/kind/{id}/{page}")
    public QueryResponseResult getNewsByKindId(@PathVariable("id") String id,@PathVariable("page") Integer page){
        System.out.println(id+" "+page);
        return newsService.getNewsByKindId(id,page);
    }

    /**
     * 根据姓名模糊查询
     * @return
     */
    @GetMapping("/name")
    public QueryResponseResult getNewsByName( String newsTitle,Integer page){
//        String newsTitle = queryNewsRequest.getNewsTitle();
//        Integer page = queryNewsRequest.getPage();
        System.out.println(newsTitle +" "+ page);
        return newsService.getNewsByName(newsTitle, page);
    }
    /**
     * 分页和排序加动态查询管理新闻页面
     * @param pageRequest
     * @return
     */
    @GetMapping("/findNewsManageList")
    public QueryResponseResult findNewsManageList(NewsPageRequest pageRequest){
        return newsService.findNewsManageList(pageRequest);
    }



    /**
     * 分页排序条件查询新闻列表
     * @param pageRequest
     * @param queryNewsRequest
     * @return
     */
    @GetMapping
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsRequest queryNewsRequest){
        return newsService.findList(pageRequest,queryNewsRequest);
    }

    /**
     * 新增新闻
     * @param news
     * @return
     */
    @PostMapping
    public NewsResult add(@RequestBody News news){
        return newsService.add(news);
    }

    /**
     * 更新新闻
     * @param id
     * @param news
     * @return
     */
    @PutMapping("/id/{id}")
    public NewsResult update(@PathVariable("id") String id,@RequestBody News news){
        return newsService.updateById(id,news);
    }

    /**
     * 删除新闻
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseResult delete(@PathVariable("id") String id){
        return newsService.deleteById(id);
    }

    /**
     * 根据id查询新闻
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public News findOne(@PathVariable("id") String id){
        return newsService.findById(id);
    }
}
