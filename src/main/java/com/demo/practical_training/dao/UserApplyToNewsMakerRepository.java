package com.demo.practical_training.dao;

import com.demo.practical_training.entity.UserApplyToNewsMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 申请成为新闻发布者持久层
 */
@Repository
public interface UserApplyToNewsMakerRepository extends
        JpaRepository<UserApplyToNewsMaker, String>//分页和排序
{

}
