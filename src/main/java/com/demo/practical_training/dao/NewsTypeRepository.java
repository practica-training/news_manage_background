package com.demo.practical_training.dao;

import com.demo.practical_training.entity.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsTypeRepository extends
        JpaRepository<NewsType, String>//分页和排序
{

        }