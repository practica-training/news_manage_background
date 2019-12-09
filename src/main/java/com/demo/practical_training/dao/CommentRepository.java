package com.demo.practical_training.dao;

import com.demo.practical_training.entity.AdminManagementLog;
import com.demo.practical_training.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户评论持久层
 */
@Repository
public interface CommentRepository extends
        JpaRepository<Comment, String>//分页和排序
{

}
