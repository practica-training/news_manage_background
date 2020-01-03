package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao. CommentRepository;
import com.demo.practical_training.entity. Comment;
import com.demo.practical_training.entity.dto.CommentDTO;
import com.demo.practical_training.manage.service.UserService;
import com.demo.practical_training.manage.service. CommentService;
import com.demo.practical_training.model.request.QueryCommentRequest;
import com.demo.practical_training.model.response. CommentResult;
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
public class CommentServiceImpl implements  CommentService {
    @Autowired
     CommentRepository  CommentRepository;
    @Autowired
    UserService UserService;
    /**
     * 分页和排序加动态查询评论页面
     *
     * @param pageRequest
     * @param queryCommentRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryCommentRequest queryCommentRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        //模糊匹配别名
//        exampleMatcher = exampleMatcher.withMatcher(" CommentTitle", ExampleMatcher.GenericPropertyMatchers.contains());
//        //创建条件值对象
         Comment  Comment = new  Comment();
//        //判断评论标题是否为空
//        if (StringUtils.isNotEmpty(query CommentRequest.get CommentName())) {
//             Comment.set CommentName(query CommentRequest.get CommentName());
//        }
        //判断评论id是否为空
        if (StringUtils.isNotEmpty(queryCommentRequest.getId())) {
             Comment.setId(queryCommentRequest.getId());
        }
        //创建条件实例对象
        Example< Comment> example = Example.of( Comment, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page< Comment> all =  CommentRepository.findAll(example, pageRequest.getPageable());
        //新建QueryResult<T> 对象
        QueryResult< Comment>  CommentQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
         CommentQueryResult.setList(all.getContent());
         CommentQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS,  CommentQueryResult);
    }

    /**
     * 新增评论
     * @param  Comment
     * @return
     */
    @Override
    public  CommentResult add( Comment  Comment) {
        Comment.setLikeNumber(0L);
        Comment.setReplyUserHasRead(0);
         Comment  Comment1 =  CommentRepository.save( Comment);
        return new  CommentResult(CommonCode.SUCCESS, Comment1);
    }

    /**
     * 根据id修改评论
     * @param id
     * @param  Comment
     * @return
     */
    @Override
    public  CommentResult updateById(String id,  Comment  Comment) {
        //根据Id查询评论
         Comment  Comment1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if( Comment1!=null){

             CommentRepository.save( Comment1);
             Comment  Comment2 =  CommentRepository.save( Comment1);
            if( Comment2!=null){
                return new  CommentResult(CommonCode.SUCCESS, Comment2);
            }

        }
        //若不存在，则返回失败
        return new  CommentResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询评论
         Comment  Comment1 = this.findById(id);
        if( Comment1!=null){
            //若存在，删除评论
             CommentRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    @Override
    public  Comment findById(String id) {
        Optional< Comment> optional =  CommentRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public QueryResponseResult findListByUserId(String userId, Integer page) {
        Pageable pageable = PageRequest.of(page-1,10);
        Page<Comment> commentListByUserId = this.CommentRepository.findCommentListByUserId(pageable,userId);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        commentListByUserId.getContent().forEach(comment -> {
            CommentDTO commentDTO = MapUtil.commentToCommentDTO(comment);
            commentDTOList.add(commentDTO);
        });
        QueryResult<CommentDTO> queryResult = new QueryResult(commentDTOList,commentListByUserId.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    @Override
    public QueryResponseResult findListByReplyUserId(String userId, Integer page) {
        Pageable pageable = PageRequest.of(page-1,10);
        Page<Comment> commentListByUserId = this.CommentRepository.findCommentListByReplyUserId(pageable,userId);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        commentListByUserId.getContent().forEach(comment -> {
            CommentDTO commentDTO = MapUtil.commentToCommentDTO(comment);
            commentDTOList.add(commentDTO);
        });
        QueryResult<CommentDTO> queryResult = new QueryResult(commentDTOList,commentListByUserId.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }
}
