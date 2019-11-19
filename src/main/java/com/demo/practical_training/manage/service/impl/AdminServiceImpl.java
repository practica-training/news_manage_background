package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.manage.dao.AdminRepository;
import com.demo.practical_training.manage.service.AdminService;
import com.demo.practical_training.model.request.QueryNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    /**
     * 分页和排序加动态查询管理员页面
     * @param pageRequest
     * @param queryNewsRequest
     * @return
     */
    @Override
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsRequest queryNewsRequest) {
        return null;
    }
}
