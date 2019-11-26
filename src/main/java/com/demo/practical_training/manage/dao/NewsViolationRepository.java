package com.demo.practical_training.manage.dao;

import com.demo.practical_training.entity.NewsViolation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 新闻违规表持久层
 */
@Repository
public interface NewsViolationRepository extends
        JpaRepository<NewsViolation, String> {
}
