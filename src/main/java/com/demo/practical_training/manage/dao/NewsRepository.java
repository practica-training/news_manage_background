package com.demo.practical_training.manage.dao;

import com.demo.practical_training.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends
        JpaRepository<News, String>//分页和排序
{

}
