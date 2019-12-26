package com.demo.practical_training.dao;

import com.demo.practical_training.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员持久层
 */
@Repository
public interface AdminRepository extends
        JpaRepository<Admin, String>//分页和排序
{
    //相对于名字相等查询，参数为name
    List<Admin> findByAdminName(String name);
}
