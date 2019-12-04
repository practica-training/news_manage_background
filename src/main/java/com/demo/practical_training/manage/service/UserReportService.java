package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.UserReport;
import com.demo.practical_training.model.request.QueryUserReportRequest;
import com.demo.practical_training.model.response.UserReportResult;

import java.util.List;

/**
 * 用户举报业务层
 */
public interface UserReportService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserReportRequest queryUserReportRequest);
    //增加用户举报
    public UserReportResult add(UserReport UserReport);
    //修改用户举报
    public UserReportResult updateById(String id, UserReport UserReport);
    //删除用户举报
    public ResponseResult deleteById(String id);
    //根据id查询用户举报
    public UserReport findById(String id);
    //根据用户id查询用户举报
    public List<UserReport> findByUserid(String userid);
}
