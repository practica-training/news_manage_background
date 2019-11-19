package com.demo.practical_training.manage.dao;

import com.demo.practical_training.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends
        JpaRepository<Admin, String>//分页和排序
{

}
