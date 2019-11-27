package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.UserViolation;
import com.demo.practical_training.model.request.QueryUserViolationRequest;
import com.demo.practical_training.model.response.UserViolationResult;

/**
 * 用户违规表业务层
 */
public interface UserViolationService {
    //页面分页与查询
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserViolationRequest queryUserViolationRequest);
    //增加用户违规
    public UserViolationResult add(UserViolation UserViolation);
    //修改用户违规
    public UserViolationResult updateById(String id, UserViolation UserViolation);
    //删除用户违规
    public ResponseResult deleteById(String id);
    //根据id查询用户违规
    public UserViolation findById(String id);
}
