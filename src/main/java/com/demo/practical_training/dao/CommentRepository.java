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
    @Query(value = "select * from comment where newsid = :newsId",
            countQuery = "SELECT count(*) from comment where newsid = :newsId",
            nativeQuery = true)
    Page<Comment> findCommentListByNewsId(Pageable pageable, String newsId);
    //可以在SQL中增加更多的条件查询
    @Query(value = "select * from comment where userid = :userId",
            countQuery = "SELECT count(*) from comment where userid = :userId",
            nativeQuery = true)
    Page<Comment> findCommentListByUserId(Pageable pageable, String userId);
 //可以在SQL中增加更多的条件查询
 @Query(value = "select * from comment where reply_user_id = :replyUserId",
         countQuery = "SELECT count(*) from comment where reply_user_id = :replyUserId",
         nativeQuery = true)
 Page<Comment> findCommentListByReplyUserId(Pageable pageable, String replyUserId);

}
