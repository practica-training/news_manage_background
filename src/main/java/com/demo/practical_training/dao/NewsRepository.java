package com.demo.practical_training.dao;

import com.demo.practical_training.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 新闻持久层
 */
@Repository
public interface NewsRepository extends
        JpaRepository<News, String>//分页和排序
{
    //可以在SQL中增加更多的条件查询
    @Query(value = "SELECT * FROM news WHERE news.news_state= -1 OR news.news_state= -3",
            countQuery = "SELECT count(*) FROM news  WHERE news.news_state= ?1 OR news.news_state= -3",
            nativeQuery = true)
    Page<News> findAllPage(Pageable pageable);


}
