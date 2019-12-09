package com.demo.practical_training.dao;

import com.demo.practical_training.entity.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户举报持久层
 */
@Repository
public interface UserReportRepository extends
        JpaRepository<UserReport, String>{
    @Query(value = "select * from user_report b where b.reportedid=?1",nativeQuery = true)
    List<UserReport> findByReportedid(String reportedid);
}
