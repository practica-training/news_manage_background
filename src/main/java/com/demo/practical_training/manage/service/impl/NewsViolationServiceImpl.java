package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.NewsViolation;
import com.demo.practical_training.dao.NewsViolationRepository;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.manage.service.NewsViolationService;
import com.demo.practical_training.model.request.QueryNewsViolationRequest;
import com.demo.practical_training.model.response.NewsViolationResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 */
@Service
@Transactional()
public class NewsViolationServiceImpl implements NewsViolationService {
    @Autowired
    NewsViolationRepository NewsViolationRepository;
    @Autowired
    NewsService newsService;
    /**
     * 分页和排序加动态查询新闻违规页面
     *
     * @param pageRequest
     * @param queryNewsViolationRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsViolationRequest queryNewsViolationRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        //模糊匹配别名
//        exampleMatcher = exampleMatcher.withMatcher("NewsViolationTitle", ExampleMatcher.GenericPropertyMatchers.contains());
//        //创建条件值对象
        NewsViolation NewsViolation = new NewsViolation();
//        //判断新闻违规标题是否为空
//        if (StringUtils.isNotEmpty(queryNewsViolationRequest.getNewsViolationName())) {
//            NewsViolation.setNewsViolationName(queryNewsViolationRequest.getNewsViolationName());
//        }
        //判断新闻违规id是否为空
        if (StringUtils.isNotEmpty(queryNewsViolationRequest.getViolationID())) {
            NewsViolation.setViolationID(queryNewsViolationRequest.getViolationID());
        }
        //创建条件实例对象
        Example<NewsViolation> example = Example.of(NewsViolation, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<NewsViolation> all = NewsViolationRepository.findAll(example, pageRequest.getPageable());
        //新建QueryResult<T> 对象
        QueryResult<NewsViolation> NewsViolationQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        NewsViolationQueryResult.setList(all.getContent());
        NewsViolationQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, NewsViolationQueryResult);
    }

    /**
     * 新增新闻违规
     * @param NewsViolation
     * @return
     */
    @Override
    public NewsViolationResult add(NewsViolation NewsViolation) {
        NewsViolation NewsViolation1 = NewsViolationRepository.save(NewsViolation);
        return new NewsViolationResult(CommonCode.SUCCESS,NewsViolation1);
    }

    /**
     * 根据id修改新闻违规
     * @param id
     * @param NewsViolation
     * @return
     */
    @Override
    public NewsViolationResult updateById(String id, NewsViolation NewsViolation) {
        //根据Id查询新闻违规
        NewsViolation NewsViolation1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(NewsViolation1!=null){

            NewsViolationRepository.save(NewsViolation1);
            NewsViolation NewsViolation2 = NewsViolationRepository.save(NewsViolation1);
            if(NewsViolation2!=null){
                return new NewsViolationResult(CommonCode.SUCCESS,NewsViolation2);
            }

        }
        //若不存在，则返回失败
        return new NewsViolationResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除新闻违规
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询新闻违规
        NewsViolation NewsViolation1 = this.findById(id);
        if(NewsViolation1!=null){
            //若存在，删除新闻违规
            NewsViolationRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询新闻违规
     * @param id
     * @return
     */
    @Override
    public NewsViolation findById(String id) {
        Optional<NewsViolation> optional = NewsViolationRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
