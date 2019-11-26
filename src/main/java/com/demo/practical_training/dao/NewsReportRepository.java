package com.demo.practical_training.dao;

import com.demo.practical_training.entity.NewsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 新闻举报持久层
 */
@Repository
public interface NewsReportRepository  extends
        JpaRepository<NewsReport, String>{
}
