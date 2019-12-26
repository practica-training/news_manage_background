package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.Admin;
import com.demo.practical_training.model.request.QueryAdminRequest;
import com.demo.practical_training.model.response.AdminResult;

import java.util.List;

/**
 * 管理员业务层
 */
public interface AdminService {
    //页面分页与查询
    public QueryResponseResult findManageList(STablePageRequest pageRequest, QueryAdminRequest queryAdminRequest);
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
    //根据name查询管理员
    public List<Admin> findByName(String name);

    //审核新闻举报
    public ResponseResult reviewNews(String id);
    //忽略新闻举报
    public ResponseResult reviewNewsMiss(String id);

    //审核用户举报
    public ResponseResult reviewUser(String id,String normalDate);
    //忽略用户举报
    public ResponseResult reviewUserMiss(String id);

    //审核新闻发布通过
    public ResponseResult reviewNewsPublishOn(String id);
    //审核新闻发布不通过
    public ResponseResult reviewNewsPublishOff(String id,String offReason);
//  对新闻下架处理
    public ResponseResult reviewNewsOff(String id,String offReason);
//  对新闻进行解除下架处理
    public ResponseResult reviewNewsOn(String id);
    //对用户进行禁言操作
    public ResponseResult reviewUserOff(String id,String offReason,String normalDate);
    //对用户进行解除禁言操作
    public ResponseResult reviewUserOn(String id);
    //对用户是实名认证审核通过
    public ResponseResult reviewUserVerifiedOn(String id);
    //对用户是实名认证审核不通过
    public ResponseResult reviewUserVerifiedOff(String id,String offReason);
    //审核用户申请为新闻发布者通过
    public ResponseResult reviewUserBecomePublishOn(String id);
    //审核用户申请为新闻发布者不通过
    public ResponseResult reviewUserBecomePublishOff(String id,String offReason);
    //管理员新闻发布者(将新闻发布者进行降级）
    public ResponseResult reviewUserBecomeUser(String id);
    //管理管理员
    public ResponseResult ManagementAdmin(String id, Integer power);
}

