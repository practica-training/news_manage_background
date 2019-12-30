package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.NewsPageRequest;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.CommentRepository;
import com.demo.practical_training.dao.NewsRepository;
import com.demo.practical_training.dao.NewsTypeRepository;
import com.demo.practical_training.entity.Comment;
import com.demo.practical_training.entity.News;
import com.demo.practical_training.entity.NewsType;
import com.demo.practical_training.entity.dto.NewsDTO;
import com.demo.practical_training.entity.dto.NewsManageDTO;
import com.demo.practical_training.manage.service.NewsService;
import com.demo.practical_training.model.request.QueryNewsRequest;
import com.demo.practical_training.model.response.NewsResult;
import com.demo.practical_training.utils.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional()
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsTypeRepository newsTypeRepository;
    @Autowired
    CommentRepository commentRepository;
    /**
     * 分页和排序加动态查询管理新闻页面
     *
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findNewsManageList(NewsPageRequest pageRequest) {

        //根据分页对象和条件实例对象查询数据
        Page<News> all = newsRepository.findAllPage(pageRequest.getPageable());
        System.out.println(all);

        //新建QueryResult<T> 对象
        List<NewsManageDTO> list = new ArrayList<>();
        for (News news1 : all) {
            NewsManageDTO newsManageDTO = new NewsManageDTO();
            newsManageDTO.setContent(news1.getContent());
            newsManageDTO.setLikeNumber(news1.getLikeNumber());
            newsManageDTO.setNewsAvatar(news1.getNewsAvatar());
            newsManageDTO.setNewsState(news1.getNewsState());
            newsManageDTO.setNewsTitle(news1.getNewsTitle());
            newsManageDTO.setReadNumber(news1.getReadNumber());
            newsManageDTO.setNewsId(news1.getId());
            if(news1.getPublishTime()!=null){
                newsManageDTO.setPublishTime(news1.getPublishTime().toString());
            }
            list.add(newsManageDTO);
        }
        QueryResult<NewsManageDTO> newsManageDTOQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        newsManageDTOQueryResult.setList(list);
        newsManageDTOQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, newsManageDTOQueryResult);
    }

    /**
     * 通过新闻状态返回新闻列表
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
        Integer newsState = queryNewsRequest.getNewsState();
        if(newsState!=null){
            news.setNewsState(newsState);
        }else {
            news.setNewsState(1);
        }
        //判断新闻标题是否为空
        if (StringUtils.isNotEmpty(queryNewsRequest.getNewsTitle())) {
            news.setNewsTitle(queryNewsRequest.getNewsTitle());
        }
        //判断新闻id是否为空
        if (StringUtils.isNotEmpty(queryNewsRequest.getId())) {
            news.setId(queryNewsRequest.getId());
        }
        //创建条件实例对象
        Example<News> example = Example.of(news, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<News> all = newsRepository.findAll(example, pageRequest.getPageable());

        //新建QueryResult<T> 对象
        List<NewsDTO> list = new ArrayList<>();
        for (News news1 : all) {
            NewsDTO newsDTO = MapUtil.newsToNewsDTO(news1);
            news1.getNewsTypeSet().size();
            list.add(newsDTO);
        }
        QueryResult<NewsDTO> newsDTOQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        newsDTOQueryResult.setList(list);
        newsDTOQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, newsDTOQueryResult);
    }

    /**
     * 新增新闻
     *
     * @param news
     * @return
     */
    @Override
    public NewsResult add(News news) {
        News news1 = newsRepository.save(news);
        return new NewsResult(CommonCode.SUCCESS, MapUtil.newsToNewsDTO(news1));
    }

    /**
     * 根据id修改新闻
     *
     * @param id
     * @param news
     * @return
     */
    @Override
    public NewsResult updateById(String id, News news) {
        //根据Id查询新闻
        News news1 = this.newsRepository.getOne(id);
        //若存在，则调用set方法更新数据，并保存
        if (news1 != null) {
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
            if (news2 != null) {
                return new NewsResult(CommonCode.SUCCESS, MapUtil.newsToNewsDTO(news2));
            }

        }
        //若不存在，则返回失败
        return new NewsResult(CommonCode.FAIL, null);
    }

    /**
     * 根据id删除新闻
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询新闻
        News news1 = this.newsRepository.getOne(id);
        if (news1 != null) {
            news1.getNewsTypeSet().size();
            //若存在，删除新闻
            newsRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询新闻
     *
     * @param id
     * @return
     */
    @Override
    public NewsResult findById(String id) {
        News news = newsRepository.getOne(id);
        if (news != null) {
            news.getNewsTypeSet().size();
            return new NewsResult(CommonCode.SUCCESS, MapUtil.newsToNewsDTO(news) );
        }
     return null;
    }

    @Override
    public QueryResponseResult getNewsKinds() {
        List<NewsType> newsTypeList = this.newsTypeRepository.findAll();
        QueryResult<NewsType> queryResult = new QueryResult(newsTypeList,newsTypeList.size());
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

    @Override
    public QueryResponseResult getNewsByKindId(String id, Integer page) {
        Pageable pageable = PageRequest.of(page-1,10);
        Page<News> newsByNewsType = this.newsRepository.findNewsByNewsType(pageable, id);
        List<NewsDTO> newsDTOList = new ArrayList<>();
        newsByNewsType.getContent().forEach(news -> {
            news.getNewsTypeSet().size();
            NewsDTO newsDTO = MapUtil.newsToNewsDTO(news);
            newsDTOList.add(newsDTO);
        });
        QueryResult<NewsDTO> queryResult = new QueryResult(newsDTOList,newsByNewsType.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    @Override
    public QueryResponseResult getNewsByName(String name, Integer page) {
        Pageable pageable = PageRequest.of(page-1,10);
        Page<News> newsByNewsType = this.newsRepository.findNewsByTitle(pageable, "%"+name+"%");
        List<NewsDTO> newsDTOList = new ArrayList<>();
        newsByNewsType.getContent().forEach(news -> {
            NewsDTO newsDTO = MapUtil.newsToNewsDTO(news);
            newsDTOList.add(newsDTO);
        });
        QueryResult<NewsDTO> queryResult = new QueryResult(newsDTOList,newsByNewsType.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    @Override
    public QueryResponseResult getNewsCommentList(String newsId,Integer page) {
        Pageable pageable = PageRequest.of(page-1,10);
        Page<Comment> commentListByNewsId = this.commentRepository.findCommentListByNewsId(pageable, newsId);
        QueryResult<News> queryResult = new QueryResult(commentListByNewsId.getContent(),commentListByNewsId.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }
}
