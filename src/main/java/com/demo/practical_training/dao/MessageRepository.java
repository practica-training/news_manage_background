package com.demo.practical_training.dao;

import com.demo.practical_training.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 消息持久层
 */
@Repository
public interface MessageRepository extends
        JpaRepository<Message, String>//分页和排序
{
        @Query(value = "SELECT * FROM message WHERE message.toid = ?1",
                nativeQuery = true)
        List<Message> findByToid(String id);
}
