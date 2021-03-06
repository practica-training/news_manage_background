package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.UserManagementLogRepository;
import com.demo.practical_training.entity.UserManagementLog;
import com.demo.practical_training.manage.service.UserManagementLogService;
import com.demo.practical_training.model.request.QueryUserManagementLogRequest;
import com.demo.practical_training.model.response.LogResult;
import com.demo.practical_training.model.response.UserManagementLogResult;
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
public class UserManagementLogServiceImpl implements UserManagementLogService {
    @Autowired
    UserManagementLogRepository UserManagementLogRepository;
    /**
     * 分页和排序加动态查询管理员的用户管理日志页面
     *
     * @param pageRequest
     * @param queryUserManagementLogRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserManagementLogRequest queryUserManagementLogRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        //模糊匹配别名
//        exampleMatcher = exampleMatcher.withMatcher("UserManagementLogTitle", ExampleMatcher.GenericPropertyMatchers.contains());
//        //创建条件值对象
        UserManagementLog UserManagementLog = new UserManagementLog();
//        //判断管理员的用户管理日志标题是否为空
//        if (StringUtils.isNotEmpty(queryUserManagementLogRequest.getUserManagementLogName())) {
//            UserManagementLog.setUserManagementLogName(queryUserManagementLogRequest.getUserManagementLogName());
//        }
        //判断管理员的用户管理日志id是否为空
        if (StringUtils.isNotEmpty(queryUserManagementLogRequest.getId())) {
            UserManagementLog.setId(queryUserManagementLogRequest.getId());
        }
        //创建条件实例对象
        Example<UserManagementLog> example = Example.of(UserManagementLog, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<UserManagementLog> all = UserManagementLogRepository.findAll(example, pageRequest.getPageable());
        List<LogResult> logResultList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年 MM月 dd日 HH时 mm分 ss秒");
        all.getContent().forEach(adminManagementLog1 -> {
            String operationalContent = adminManagementLog1.getOperationalContent();
            String time = dateFormat.format(new Date(adminManagementLog1.getProcessingTime().getTime()));
            LogResult logResult = new LogResult(operationalContent,time);
            logResultList.add(logResult);
        });
        //新建QueryResult<T> 对象
        QueryResult<LogResult> userManagementLogQueryResult = new QueryResult<>();
        userManagementLogQueryResult.setList(logResultList);
        userManagementLogQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, userManagementLogQueryResult);
    }

    /**
     * 新增管理员的用户管理日志
     * @param UserManagementLog
     * @return
     */
    @Override
    public UserManagementLogResult add(UserManagementLog UserManagementLog) {
        UserManagementLog UserManagementLog1 = UserManagementLogRepository.save(UserManagementLog);
        return new UserManagementLogResult(CommonCode.SUCCESS,UserManagementLog1);
    }

    /**
     * 根据id修改管理员的用户管理日志
     * @param id
     * @param UserManagementLog
     * @return
     */
    @Override
    public UserManagementLogResult updateById(String id, UserManagementLog UserManagementLog) {
        //根据Id查询管理员的用户管理日志
        UserManagementLog UserManagementLog1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(UserManagementLog1!=null){
            
            UserManagementLogRepository.save(UserManagementLog1);
            UserManagementLog UserManagementLog2 = UserManagementLogRepository.save(UserManagementLog1);
            if(UserManagementLog2!=null){
                return new UserManagementLogResult(CommonCode.SUCCESS,UserManagementLog2);
            }

        }
        //若不存在，则返回失败
        return new UserManagementLogResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除管理员的用户管理日志
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询管理员的用户管理日志
        UserManagementLog UserManagementLog1 = this.findById(id);
        if(UserManagementLog1!=null){
            //若存在，删除管理员的用户管理日志
            UserManagementLogRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询管理员的用户管理日志
     * @param id
     * @return
     */
    @Override
    public UserManagementLog findById(String id) {
        Optional<UserManagementLog> optional = UserManagementLogRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
