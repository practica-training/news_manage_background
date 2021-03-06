package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.NewsReportRepository;
import com.demo.practical_training.entity.NewsReport;
import com.demo.practical_training.entity.dto.NewsReportDTO;
import com.demo.practical_training.manage.service.NewsReportService;
import com.demo.practical_training.model.request.QueryNewsReportRequest;
import com.demo.practical_training.model.response.NewsReportResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional()
public class NewsReportServiceImpl implements NewsReportService {
    @Autowired
    NewsReportRepository newsReportRepository;
    /**
     * 分页和排序加动态查询新闻举报页面
     *
     * @param pageRequest
     * @param queryNewsReportRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryNewsReportRequest queryNewsReportRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        //模糊匹配别名
//        exampleMatcher = exampleMatcher.withMatcher("NewsReportTitle", ExampleMatcher.GenericPropertyMatchers.contains());
//        //创建条件值对象
        NewsReport NewsReport = new NewsReport();
//        //判断新闻举报标题是否为空
//        if (StringUtils.isNotEmpty(queryNewsReportRequest.getNewsReportName())) {
//            NewsReport.setNewsReportName(queryNewsReportRequest.getNewsReportName());
//        }
        //判断新闻举报id是否为空
        if (StringUtils.isNotEmpty(queryNewsReportRequest.getId())) {
            NewsReport.setId(queryNewsReportRequest.getId());
        }
        NewsReport.setReviewState(0);
        //创建条件实例对象
        Example<NewsReport> example = Example.of(NewsReport, exampleMatcher);
//        System.out.println(example);
//        System.out.println(pageRequest);

        //根据分页对象和条件实例对象查询数据
        Page<NewsReport> all = newsReportRepository.findAll(example, pageRequest.getPageable());
        //新建QueryResult<T> 对象
        List<NewsReportDTO> list = new ArrayList<>();
        for (NewsReport newsReport : all) {
            NewsReportDTO newsReportDTO = new NewsReportDTO();
            newsReportDTO.setId(newsReport.getId());
            newsReportDTO.setNewsId(newsReport.getNews().getId());
            newsReportDTO.setNewsTitle(newsReport.getNews().getNewsTitle());
            newsReportDTO.setReason(newsReport.getReportReason());
            newsReportDTO.setReportTime(newsReport.getReportTime().toString());
            newsReportDTO.setUserName(newsReport.getUser().getUserName());
            list.add(newsReportDTO);
        }
        QueryResult<NewsReportDTO> NewsReportQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        NewsReportQueryResult.setList(list);
        NewsReportQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, NewsReportQueryResult);
    }

    /**
     * 新增新闻举报
     * @param NewsReport
     * @return
     */
    @Override
    public NewsReportResult add(NewsReport NewsReport) {
        NewsReport.setReviewState(0);
        NewsReport NewsReport1 = newsReportRepository.saveAndFlush(NewsReport);
        return new NewsReportResult(CommonCode.SUCCESS,NewsReport1);
    }

    /**
     * 根据id修改新闻举报
     * @param id
     * @param newsReport
     * @return
     */
    @Override
    public NewsReportResult updateById(String id, NewsReport newsReport) {
        //根据Id查询新闻举报
        NewsReport newsReport1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(newsReport1!=null){
            NewsReport newsReport2 = newsReportRepository.save(newsReport);
            if(newsReport2!=null){
                return new NewsReportResult(CommonCode.SUCCESS,newsReport2);
            }

        }
        //若不存在，则返回失败
        return new NewsReportResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除新闻举报
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询新闻举报
        NewsReport NewsReport1 = this.findById(id);
        if(NewsReport1!=null){
            //若存在，删除新闻举报
            newsReportRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询新闻举报
     * @param id
     * @return
     */
    @Override
    public NewsReport findById(String id) {
        Optional<NewsReport> optional = newsReportRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    /**
     * 根据newsid查询新闻举报
     * @param newsid
     * @return
     */
    @Override
    public List<NewsReport> findByNewsid(String newsid) {
        List<NewsReport> list = newsReportRepository.findByNewsid(newsid);
        return list;
    }

}
