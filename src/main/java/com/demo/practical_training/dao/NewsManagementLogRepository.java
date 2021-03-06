package com.demo.practical_training.dao;

import com.demo.practical_training.entity.NewsManagementLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 管理员的新闻管理日志持久层
 */
@Repository
public interface NewsManagementLogRepository extends
        JpaRepository<NewsManagementLog, String>//分页和排序
{

}
