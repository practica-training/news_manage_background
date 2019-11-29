package com.demo.practical_training.dao;

import com.demo.practical_training.entity.NewsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 新闻举报持久层
 */
@Repository
public interface NewsReportRepository  extends
        JpaRepository<NewsReport, String>{
    @Query(value = "select * from news_report b where b.newsid=?1",nativeQuery = true)
    List<NewsReport> findByNewsid(String newsid);
}
