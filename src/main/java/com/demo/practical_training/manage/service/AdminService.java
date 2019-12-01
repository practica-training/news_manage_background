package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.User;
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
    //根据id查询管理员
    public Admin findByName(String name);
    //审核新闻举报
    public ResponseResult reviewNews(String id);
    //审核新闻发布
    public ResponseResult reviewNewsPublish(String id, News news);
//    //对新闻下架处理
//    public ResponseResult reviewNewsOff(String id, News news);
    //审核用户举报
    public ResponseResult reviewUser(String id);
//    //对用户封号处理
//    public ResponseResult reviewUserOff(String id, User user);
    //对用户是实名认证处理
    public ResponseResult reviewUserVerified(String id, User user);
    //审核用户申请为新闻发布者
    public ResponseResult reviewUserBecomePublish(String id, User user);
    //管理员新闻发布者(将新闻发布者进行降级）
    public ResponseResult reviewUserBecomeUser(String id, User user);
    //管理管理员
    public ResponseResult ManagementAdmin(String id, Admin admin);
}

