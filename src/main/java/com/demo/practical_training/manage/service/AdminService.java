package com.demo.practical_training.manage.service;

import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.model.request.QueryNewsRequest;

public interface AdminService {
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsRequest queryNewsRequest);
}
