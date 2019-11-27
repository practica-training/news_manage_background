package com.demo.practical_training.dao;

import com.demo.practical_training.entity.UserViolation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 新闻违规表持久层
 */
@Repository
public interface UserViolationRepository extends
        JpaRepository<UserViolation, String> {
}
