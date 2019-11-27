package com.demo.practical_training.dao;

import com.demo.practical_training.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 管理员持久层
 */
@Repository
public interface AdminRepository extends
        JpaRepository<Admin, String>//分页和排序
{

}
