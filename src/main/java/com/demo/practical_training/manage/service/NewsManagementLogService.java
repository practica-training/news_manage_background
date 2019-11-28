package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.NewsManagementLog;
import com.demo.practical_training.model.request.QueryNewsManagementLogRequest;
import com.demo.practical_training.model.response.NewsManagementLogResult;

/**
 * 管理员的新闻管理日志业务层
 */
public interface NewsManagementLogService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsManagementLogRequest queryNewsManagementLogRequest);
    //增加管理员的新闻管理日志
    public NewsManagementLogResult add(NewsManagementLog NewsManagementLog);
    //修改管理员的新闻管理日志
    public NewsManagementLogResult updateById(String id, NewsManagementLog NewsManagementLog);
    //删除管理员的新闻管理日志
    public ResponseResult deleteById(String id);
    //根据id查询管理员的新闻管理日志
    public NewsManagementLog findById(String id);
}
