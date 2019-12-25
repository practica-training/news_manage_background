package com.demo.practical_training.dao;

import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    //可以在SQL中增加更多的条件查询
    @Query(value = "SELECT * FROM news WHERE news.id in (select newsid from news_and_type where news_typeid = :newsTypeId)",
            countQuery = "SELECT count(*) FROM news WHERE news.id in (select id from news_and_type where id = :newsTypeId)",
            nativeQuery = true)
    Page<News> findNewsByNewsType(Pageable pageable,String newsTypeId);

    //可以在SQL中增加更多的条件查询
    @Query(value = "SELECT * FROM news WHERE news.news_title like :name",
            countQuery = "SELECT count(*) FROM news WHERE news.news_title like :name",
            nativeQuery = true)
    Page<News> findNewsByTitle(Pageable pageable,String name);

}
