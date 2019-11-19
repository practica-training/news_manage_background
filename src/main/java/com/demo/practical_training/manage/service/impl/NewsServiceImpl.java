package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.manage.dao.NewsRepository;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.model.request.QueryNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsRepository newsRepository;
    /**
     * 分页和排序加动态查询新闻页面
     *
     * @param pageRequest
     * @param queryNewsRequest
     * @return
     */
    @Override
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsRequest queryNewsRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        //模糊匹配别名
        exampleMatcher = exampleMatcher.withMatcher("newsTitle", ExampleMatcher.GenericPropertyMatchers.contains());
        //创建条件值对象
        News news = new News();
        //判断新闻标题是否为空
        if (StringUtils.isEmpty(queryNewsRequest.getNewsTitle())) {
            news.setNewsTitle(queryNewsRequest.getNewsTitle());
        }
        //判断新闻id是否为空
        if (StringUtils.isEmpty(queryNewsRequest.getNewsId())) {
            news.setNewsID(queryNewsRequest.getNewsId());
        }
        //创建条件实例对象
        Example<News> example = Example.of(news, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<News> all = newsRepository.findAll(example, pageRequest.getPageable());
        //新建QueryResult<T> 对象
        QueryResult<News> newsQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        newsQueryResult.setList(all.getContent());
        newsQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, newsQueryResult);
    }
}
