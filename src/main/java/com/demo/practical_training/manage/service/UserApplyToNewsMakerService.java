package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.UserApplyToNewsMaker;
import com.demo.practical_training.model.request.QueryUserApplyToNewsMakerRequest;
import com.demo.practical_training.model.response.UserApplyToNewsMakerResult;

/**
 * 申请成为新闻发布者表业务层
 */
public interface UserApplyToNewsMakerService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserApplyToNewsMakerRequest queryUserApplyToNewsMakerRequest);
    //增加申请成为新闻发布者
    public UserApplyToNewsMakerResult add(UserApplyToNewsMaker UserApplyToNewsMaker);
    //修改申请成为新闻发布者
    public UserApplyToNewsMakerResult updateById(String id, UserApplyToNewsMaker UserApplyToNewsMaker);
    //删除申请成为新闻发布者
    public ResponseResult deleteById(String id);
    //根据id查询申请成为新闻发布者
    public UserApplyToNewsMaker findById(String id);
}
