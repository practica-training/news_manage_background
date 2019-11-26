package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.model.request.QueryAdminRequest;
import com.demo.practical_training.model.response.AdminResult;

/**
 * 管理员业务层
 */
public interface AdminService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryAdminRequest queryAdminRequest);
    //增加管理员
    public AdminResult add(Admin Admin);
    //修改管理员
    public AdminResult updateById(String id,Admin Admin);
    //删除管理员
    public ResponseResult deleteById(String id);
    //根据id查询管理员
    public Admin findById(String id);
    //审核新闻举报
    public ResponseResult reviewNews(String id);
}
