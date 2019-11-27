package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.Const;
import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.AdminRepository;
import com.demo.practical_training.entity.*;
import com.demo.practical_training.manage.service.*;
import com.demo.practical_training.model.request.QueryAdminRequest;
import com.demo.practical_training.model.response.AdminCode;
import com.demo.practical_training.model.response.AdminResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional()
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    NewsReportService newsReportService;
    @Autowired
    NewsViolationService newsViolationService;
    @Autowired
    NewsService newsService;
    @Autowired
    UserReportService userReportService;
    @Autowired
    UserViolationService userViolationService;
    @Autowired
    UserService userService;
    /**
     * 分页和排序加动态查询管理员页面
     *
     * @param pageRequest
     * @param queryAdminRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryAdminRequest queryAdminRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        //模糊匹配别名
        exampleMatcher = exampleMatcher.withMatcher("AdminTitle", ExampleMatcher.GenericPropertyMatchers.contains());
        //创建条件值对象
        Admin Admin = new Admin();
        //判断管理员标题是否为空
        if (StringUtils.isNotEmpty(queryAdminRequest.getAdminName())) {
            Admin.setAdminName(queryAdminRequest.getAdminName());
        }
        //判断管理员id是否为空
        if (StringUtils.isNotEmpty(queryAdminRequest.getAdminID())) {
            Admin.setAdminID(queryAdminRequest.getAdminID());
        }
        //创建条件实例对象
        Example<Admin> example = Example.of(Admin, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<Admin> all = adminRepository.findAll(example, pageRequest.getPageable());
        //新建QueryResult<T> 对象
        QueryResult<Admin> AdminQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        AdminQueryResult.setList(all.getContent());
        AdminQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, AdminQueryResult);
    }

    /**
     * 新增管理员
     * @param Admin
     * @return
     */
    @Override
    public AdminResult add(Admin Admin) {
        Admin Admin1 = adminRepository.save(Admin);
        return new AdminResult(CommonCode.SUCCESS,Admin1);
    }

    /**
     * 根据id修改管理员
     * @param id
     * @param admin
     * @return
     */
    @Override
    public AdminResult updateById(String id, Admin admin) {
        //根据Id查询管理员
        Admin admin1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(admin1!=null){
            //设置管理员用户名
            admin1.setAdminName(admin.getAdminName());
            //设置管理员登陆密码
            admin1.setAdminPassword(admin.getAdminPassword());
            //设置管理员头像路径
            admin1.setAdminAvatar(admin.getAdminAvatar());
            //设置管理员权限等级
            admin1.setPower(admin.getPower());
            adminRepository.save(admin1);
            Admin admin2 = adminRepository.save(admin1);
            if(admin2!=null){
                return new AdminResult(CommonCode.SUCCESS,admin2);
            }

        }
        //若不存在，则返回失败
        return new AdminResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除管理员
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询管理员
        Admin Admin1 = this.findById(id);
        if(Admin1!=null){
            //若存在，删除管理员
            adminRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询管理员
     * @param id
     * @return
     */
    @Override
    public Admin findById(String id) {
        Optional<Admin> optional = adminRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    /**
     * 审核新闻举报
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewNews(String id) {
        //1.根据id查询新闻举报
        NewsReport newsReport = newsReportService.findById(id);
        //2.审核状态 0等待审核 1审核完成  是否违规 0没有违规 1违规
        if(newsReport.getReviewState() == Const.NEWS_UNDER_REVIEW && newsReport.getIsIllegal()==1){
            //2.1提示用户举报成功，已加入违规新闻
            //新建违规新闻
            NewsViolation newsViolation = new NewsViolation();
            newsViolation.setNews(newsReport.getNews());
            newsViolation.setViolationReason(newsReport.getReportReason());
            Date date = new Date();
            newsViolation.setReviewTime(new Timestamp(date.getTime()));
            newsReport.getNews().setNewsState(Const.NEWS_DISABLE);
            newsViolationService.add(newsViolation);
            return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWS);
        }else if(newsReport.getReviewState() == Const.NEWS_DRAFT){
            //2.2提示等待审核
            return new ResponseResult(AdminCode.ADMIN_WAIT_NEWS);
        }else{
            //2.3否则，提示用户举报失败
            return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_NEWS);
        }
    }

    /**
     * 审核新闻发布
     * @param id
     * @param news
     * @return
     */
    @Override
    public ResponseResult reviewNewsPublish(String id, News news) {
        //1.调用News更新方法
        newsService.updateById(id, news);
        if(news.getNewsState()==Const.NEWS_AUDIT_FAILURE){
            return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_NEWSPUBLISH);
        }else if(news.getNewsState()==Const.NEWS_PUBLISH){
            return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWSPUBLISH);
        }
        return new ResponseResult(AdminCode.ADMIN_NEWSPUBLISH_WAIT);
    }

    /**
     * 对新闻下架处理
     * @param id
     * @param news
     * @return
     */
    @Override
    public ResponseResult reviewNewsOff(String id, News news) {
        //1.调用News更新方法
        newsService.updateById(id, news);
        if(news.getNewsState()==Const.NEWS_OFF){
            return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWSEXISTENCE);
        }
        return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_NEWEXISTENCE);
    }

    /**
     * 审核用户举报
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUser(String id) {
        //1.根据id查询用户举报
        UserReport userReport = userReportService.findById(id);
        //2.审核状态 0等待审核 1审核完成
        if(userReport.getReviewState() == Const.USER_NORMAL_USER && userReport.getIsIllegal()==1){
            //2.1提示用户举报成功，已加入违规用户
            //新建违规用户
            UserViolation UserViolation = new UserViolation();
            UserViolation.setUser(userReport.getUser());
            UserViolation.setReason(userReport.getReportReason());
            Date date = new Date();
            UserViolation.setEndTime(new Timestamp(date.getTime()));
            userReport.getUser().setUserState(Const.USER_NORMAL_USER);
            userViolationService.add(UserViolation);
            return new ResponseResult(AdminCode.ADMIN_ALLOW_REVIEW);

        }else if(userReport.getReviewState() == Const.USER_LOGGED_OUT){
            //2.2提示等待审核
            return new ResponseResult(AdminCode.ADMIN_WAIT_NEWS);
        }else{
            //2.3否则，提示用户举报失败
            return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_REVIEW);
        }
    }

    /**
     * 对用户封号处理
     * @param id
     * @param user
     * @return
     */
    @Override
    public ResponseResult reviewUserOff(String id, User user) {
        //1.调用User更新方法
        userService.updateById(id, user);
        if(user.getUserState()== Const.NEWS_OFF){
            return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_USEREXISTENCE);
        }
        return new ResponseResult(AdminCode.ADMIN_ALLOW_USEREXISTENCE);
    }

    /**
     * 对用户实名认证处理
     * @param id
     * @param user
     * @return
     */
    @Override
    public ResponseResult reviewUserVerified(String id, User user) {
        //1.调用User更新方法
        userService.updateById(id, user);
        if(user.getIsCertified()==Const.USER_VERIFIED){
            return new ResponseResult(AdminCode.ADMIN_ALLOW_USER);
        }
        return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_USER);
    }

}
