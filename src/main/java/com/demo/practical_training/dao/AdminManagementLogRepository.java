package com.demo.practical_training.dao;

import com.demo.practical_training.entity.AdminManagementLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 超级管理员管理普通管理员的日志持久层
 */
@Repository
public interface AdminManagementLogRepository extends
        JpaRepository<AdminManagementLog, String>//分页和排序
{

}
