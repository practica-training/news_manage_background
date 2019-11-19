package com.demo.practical_training.user.dao;

import com.demo.practical_training.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends
        PagingAndSortingRepository<User, Long>//分页和排序
        , JpaSpecificationExecutor<User>//动态查询
{

}
