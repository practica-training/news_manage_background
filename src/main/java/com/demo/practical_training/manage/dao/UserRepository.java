package com.demo.practical_training.manage.dao;

import com.demo.practical_training.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户持久层
 */
@Repository
public interface UserRepository extends
        JpaRepository<User, String>//分页和排序
{

}
