package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.NewsReport;
import com.demo.practical_training.model.request.QueryNewsReportRequest;
import com.demo.practical_training.model.response.NewsReportResult;

/**
 * 新闻举报业务层
 */
public interface NewsReportService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsReportRequest queryNewsReportRequest);
    //增加新闻举报
    public NewsReportResult add(NewsReport NewsReport);
    //修改新闻举报
    public NewsReportResult updateById(String id, NewsReport NewsReport);
    //删除新闻举报
    public ResponseResult deleteById(String id);
    //根据id查询新闻举报
    public NewsReport findById(String id);
}
