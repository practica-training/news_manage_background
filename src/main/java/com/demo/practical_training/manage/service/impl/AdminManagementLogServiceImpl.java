package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.AdminManagementLogRepository;
import com.demo.practical_training.entity.AdminManagementLog;
import com.demo.practical_training.manage.service.AdminManagementLogService;
import com.demo.practical_training.model.request.QueryAdminManagementLogRequest;
import com.demo.practical_training.model.response.AdminManagementLogResult;
import com.demo.practical_training.model.response.LogResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional()
public class AdminManagementLogServiceImpl implements AdminManagementLogService {
    @Autowired
    AdminManagementLogRepository AdminManagementLogRepository;
    /**
     * 分页和排序加动态查询超级管理员管理普通管理员的日志页面
     *
     * @param pageRequest
     * @param queryAdminManagementLogRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryAdminManagementLogRequest queryAdminManagementLogRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        //模糊匹配别名
//        exampleMatcher = exampleMatcher.withMatcher("AdminManagementLogTitle", ExampleMatcher.GenericPropertyMatchers.contains());
//        //创建条件值对象
        AdminManagementLog adminManagementLog = new AdminManagementLog();
//        //判断超级管理员管理普通管理员的日志标题是否为空
//        if (StringUtils.isNotEmpty(queryAdminManagementLogRequest.getAdminManagementLogName())) {
//            AdminManagementLog.setAdminManagementLogName(queryAdminManagementLogRequest.getAdminManagementLogName());
//        }
        //判断超级管理员管理普通管理员的日志id是否为空
        if (StringUtils.isNotEmpty(queryAdminManagementLogRequest.getId())) {
            adminManagementLog.setId(queryAdminManagementLogRequest.getId());
        }
        //创建条件实例对象
        Example<AdminManagementLog> example = Example.of(adminManagementLog, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<AdminManagementLog> all = AdminManagementLogRepository.findAll(example, pageRequest.getPageable());
        List<LogResult> logResultList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年 MM月 dd日 HH时 mm分 ss秒");
        all.getContent().forEach(adminManagementLog1 -> {
            String operationalContent = adminManagementLog1.getOperationalContent();
            String time = dateFormat.format(new Date(adminManagementLog1.getProcessingTime().getTime()));
            LogResult logResult = new LogResult(operationalContent,time);
            logResultList.add(logResult);
        });
        //新建QueryResult<T> 对象
        QueryResult<LogResult> adminManagementLogQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        adminManagementLogQueryResult.setList(logResultList);
        adminManagementLogQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, adminManagementLogQueryResult);
    }

    /**
     * 新增超级管理员管理普通管理员的日志
     * @param AdminManagementLog
     * @return
     */
    @Override
    public AdminManagementLogResult add(AdminManagementLog AdminManagementLog) {
        AdminManagementLog AdminManagementLog1 = AdminManagementLogRepository.save(AdminManagementLog);
        return new AdminManagementLogResult(CommonCode.SUCCESS,AdminManagementLog1);
    }

    /**
     * 根据id修改超级管理员管理普通管理员的日志
     * @param id
     * @param AdminManagementLog
     * @return
     */
    @Override
    public AdminManagementLogResult updateById(String id, AdminManagementLog AdminManagementLog) {
        //根据Id查询超级管理员管理普通管理员的日志
        AdminManagementLog AdminManagementLog1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(AdminManagementLog1!=null){
            
            AdminManagementLogRepository.save(AdminManagementLog1);
            AdminManagementLog AdminManagementLog2 = AdminManagementLogRepository.save(AdminManagementLog1);
            if(AdminManagementLog2!=null){
                return new AdminManagementLogResult(CommonCode.SUCCESS,AdminManagementLog2);
            }

        }
        //若不存在，则返回失败
        return new AdminManagementLogResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除超级管理员管理普通管理员的日志
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询超级管理员管理普通管理员的日志
        AdminManagementLog AdminManagementLog1 = this.findById(id);
        if(AdminManagementLog1!=null){
            //若存在，删除超级管理员管理普通管理员的日志
            AdminManagementLogRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询超级管理员管理普通管理员的日志
     * @param id
     * @return
     */
    @Override
    public AdminManagementLog findById(String id) {
        Optional<AdminManagementLog> optional = AdminManagementLogRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
