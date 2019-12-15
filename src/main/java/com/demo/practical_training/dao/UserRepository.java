package com.demo.practical_training.dao;

import com.demo.practical_training.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 用户持久层
 */
@Repository
public interface UserRepository extends
        JpaRepository<User, String>//分页和排序
{
    //可以在SQL中增加更多的条件查询
    @Query(value = "SELECT * FROM user WHERE user.user_state = 1 or user.user_state = -1 ORDER BY ?#{#pageable}",
            countQuery = "SELECT count(*) FROM user WHERE user.user_state = 1 or user.user_state = -1",
            nativeQuery = true)
    Page<User> findAllByPage(Pageable pageable);
}
