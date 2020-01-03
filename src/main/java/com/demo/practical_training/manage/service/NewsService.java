package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.NewsPageRequest;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.model.request.QueryNewsRequest;
import com.demo.practical_training.model.response.NewsResult;

/**
 * 新闻业务层
 */
public interface NewsService {
    //分页和排序加动态查询管理新闻页面
    public QueryResponseResult findNewsManageList(NewsPageRequest pageRequest);
    //返回新闻轮播图列表
    public QueryResponseResult findNewsCarouselList();
    //通过新闻状态返回新闻列表
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsRequest queryNewsRequest);
    //增加新闻
    public NewsResult add(News news);
    //修改新闻
    public NewsResult updateById(String id,News news);
    //删除新闻
    public ResponseResult deleteById(String id);
    //根据id查询新闻
    public NewsResult findById(String id);

    QueryResponseResult getNewsKinds();

    QueryResponseResult getNewsByKindId(String id, Integer page);

    QueryResponseResult getNewsByName(String name, Integer page);

    QueryResponseResult getNewsCommentList(String newsId, Integer page);
}
