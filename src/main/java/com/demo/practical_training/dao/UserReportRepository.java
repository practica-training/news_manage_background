package com.demo.practical_training.dao;

import com.demo.practical_training.entity.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户举报持久层
 */
@Repository
public interface UserReportRepository extends
        JpaRepository<UserReport, String>{
}
