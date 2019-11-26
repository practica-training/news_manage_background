package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.NewsViolation;
import com.demo.practical_training.model.request.QueryNewsViolationRequest;
import com.demo.practical_training.model.response.NewsViolationResult;

/**
 * 新闻违规表业务层
 */
public interface NewsViolationService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsViolationRequest queryNewsViolationRequest);
    //增加新闻违规
    public NewsViolationResult add(NewsViolation NewsViolation);
    //修改新闻违规
    public NewsViolationResult updateById(String id, NewsViolation NewsViolation);
    //删除新闻违规
    public ResponseResult deleteById(String id);
    //根据id查询新闻违规
    public NewsViolation findById(String id);
}
