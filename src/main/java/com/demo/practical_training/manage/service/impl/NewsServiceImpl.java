package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.dao.NewsRepository;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.model.request.QueryNewsRequest;
import com.demo.practical_training.model.response.NewsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Service
@Transactional()
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
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsRequest queryNewsRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        //模糊匹配别名
        exampleMatcher = exampleMatcher.withMatcher("newsTitle", ExampleMatcher.GenericPropertyMatchers.contains());
        //创建条件值对象
        News news = new News();
        //判断新闻标题是否为空
        if (StringUtils.isNotEmpty(queryNewsRequest.getNewsTitle())) {
            news.setNewsTitle(queryNewsRequest.getNewsTitle());
        }
        //判断新闻id是否为空
        if (StringUtils.isNotEmpty(queryNewsRequest.getNewsId())) {
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

    /**
     * 新增新闻
     * @param news
     * @return
     */
    @Override
    public NewsResult add(News news) {
        News news1 = newsRepository.save(news);
        return new NewsResult(CommonCode.SUCCESS,news1);
    }

    /**
     * 根据id修改新闻
     * @param id
     * @param news
     * @return
     */
    @Override
    public NewsResult updateById(String id, News news) {
        //根据Id查询新闻
        News news1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(news1!=null){
            //设置阅读数
            news1.setReadNumber(news.getReadNumber());
            //设置新闻状态 0草稿 1审核中 2审核失败 3等待发布 4已发布 -1已删除 -2违规删除
            news1.setNewsState(news.getNewsState());
            //设置点赞数
            news1.setLikeNumber(news.getLikeNumber());
            //设置新闻创建时间
            news1.setCreateTime(news.getCreateTime());
            //设置新闻内容
            news1.setContent(news.getContent());
            //设置新闻标题
            news1.setNewsTitle(news.getNewsTitle());
            //设置新闻发布时间
            news1.setPublishTime(news.getPublishTime());
            //设置失败原因（如果审核失败则会有）
            news1.setFailureReason(news.getFailureReason());
            //设置新闻封面路径
            news1.setNewsAvatar(news.getNewsAvatar());
            //设置新闻权重 0普通新闻 1轮播图新闻
            news1.setNewsWeights(news.getNewsWeights());
            News news2 = newsRepository.save(news1);
            if(news2!=null){
                return new NewsResult(CommonCode.SUCCESS,news2);
            }

        }
        //若不存在，则返回失败
        return new NewsResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除新闻
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询新闻
        News news1 = this.findById(id);
        if(news1!=null){
            //若存在，删除新闻
            newsRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询新闻
     * @param id
     * @return
     */
    @Override
    public News findById(String id) {
        Optional<News> optional = newsRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
