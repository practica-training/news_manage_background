package com.demo.practical_training.dao;

import com.demo.practical_training.entity.AdminManagementLog;
import com.demo.practical_training.entity.Comment;
import com.demo.practical_training.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 用户评论持久层
 */
@Repository
public interface CommentRepository extends
        JpaRepository<Comment, String>//分页和排序
{
    //可以在SQL中增加更多的条件查询
    @Query(value = "select * from news where ",
            countQuery = "SELECT count(*) FROM news WHERE news.news_title like :name",
            nativeQuery = true)
    Page<Comment> findNewsByTitle(Pageable pageable, String name);
}
