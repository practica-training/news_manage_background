package com.demo.practical_training.dao;

import com.demo.practical_training.entity.UserManagementLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 管理员的用户管理日志持久层
 */
@Repository
public interface UserManagementLogRepository extends
        JpaRepository<UserManagementLog, String>//分页和排序
{

}
