package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.model.request.QueryNewsRequest;
import com.demo.practical_training.model.response.NewsResult;

/**
 * 新闻业务层
 */
public interface NewsService {
    //页面分页与查询 管理新闻
    public QueryResponseResult findNewsManageList(Integer pageNum,Integer pageSize);
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsRequest queryNewsRequest);
    //增加新闻
    public NewsResult add(News news);
    //修改新闻
    public NewsResult updateById(String id,News news);
    //删除新闻
    public ResponseResult deleteById(String id);
    //根据id查询新闻
    public News findById(String id);
}
