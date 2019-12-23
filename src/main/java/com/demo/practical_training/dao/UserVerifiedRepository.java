package com.demo.practical_training.dao;

import com.demo.practical_training.entity.UserVerified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 实名认证持久层
 */
@Repository
public interface UserVerifiedRepository extends
        JpaRepository<UserVerified, String>//分页和排序
{

}
