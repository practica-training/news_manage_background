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
    @Query(value = "select * from user_report b where b.userid=?1",nativeQuery = true)
    List<UserReport> findByUserid(String userid);
}
