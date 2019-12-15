package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.Const;
import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.dao.*;
import com.demo.practical_training.entity.*;
import com.demo.practical_training.entity.dto.AdminDTO;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional()
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    NewsReportService newsReportService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    UserReportRepository userReportRepository;
    @Autowired
    UserVerifiedRepository userVerifiedRepository;
    @Autowired
    UserApplyToNewsMakerRepository userApplyToNewsMakerRepository;
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
    @Autowired
    AdminManagementLogService adminManagementLogService;
    @Autowired
    NewsManagementLogService newsManagementLogService;
    @Autowired
    UserManagementLogService userManagementLogService;
    @Autowired
    UserVerifiedService userVerifiedService;
    @Autowired
    UserApplyToNewsMakerService userApplyToNewsMakerService;


    /**
     * 分页和排序加动态查询 管理管理员  页面
     *
     * @param pageRequest
     * @param queryAdminRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findManageList(STablePageRequest pageRequest, QueryAdminRequest queryAdminRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();

        //创建条件值对象
        Admin Admin = new Admin();
        //判断管理员id是否为空
        if (StringUtils.isNotEmpty(queryAdminRequest.getId())) {
            Admin.setId(queryAdminRequest.getId());
        }
        //创建条件实例对象
        Example<Admin> example = Example.of(Admin, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<Admin> all = adminRepository.findAll(example, pageRequest.getPageable());
        List<AdminDTO> list = new ArrayList<>();
        for (Admin admin : all) {
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setAdminId(admin.getId());
            adminDTO.setAdminName(admin.getAdminName());
            adminDTO.setPower(admin.getPower());
            list.add(adminDTO);
        }
        QueryResult<AdminDTO> adminDTOQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        adminDTOQueryResult.setList(list);
        adminDTOQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, adminDTOQueryResult);

    }

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
        //创建条件值对象
        Admin Admin = new Admin();
        //判断管理员id是否为空
        if (StringUtils.isNotEmpty(queryAdminRequest.getId())) {
            Admin.setId(queryAdminRequest.getId());
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
     * 根据name查询管理员
     * @param name
     * @return
     */
    @Override
    public Admin findByName(String name) {
        Optional<Admin> optional = adminRepository.findById(name);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    /**
     * 审核新闻举报
     *  1将所有有关该新闻的举报全部改成违规
     *  *将新闻进行下架
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewNews(String id) {
        //1.根据id查询新闻
        News news = newsService.findById(id);
        List<NewsReport> list = newsReportService.findByNewsid(news.getId());
        for (NewsReport newsReport : list) {
            newsReport.setReviewState(1);
            newsReportService.updateById(newsReport.getId(),newsReport);
            NewsViolation newsViolation = new NewsViolation();
            newsViolation.setNews(newsReport.getNews());
            newsViolation.setViolationReason(newsReport.getReportReason());
            Date date = new Date();
            newsViolation.setReviewTime(new Timestamp(date.getTime()));
            newsReport.getNews().setNewsState(Const.NEWS_DISABLE);
            newsViolationService.add(newsViolation);
        }
        news.setNewsState(Const.NEWS_OFF);
        newsService.updateById(id, news);
        return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWSEXISTENCE);
    }

    /**
     * 审核新闻发布成功
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewNewsPublishOn(String id) {
        News news = newsService.findById(id);
        news.setNewsState(3);
        Date date = new Date();
        news.setPublishTime(new Timestamp(date.getTime()));
        newsRepository.save(news);
        return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWSPUBLISH);
    }

    /**
     * 审核新闻发布失败
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewNewsPublishOff(String id,String offReason) {
        News news = newsService.findById(id);
        news.setNewsState(Const.NEWS_AUDIT_FAILURE);
        news.setFailureReason(offReason);
        newsRepository.save(news);
        return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_NEWSPUBLISH);
    }

    /**
     * 审核用户举报
     * @param user1
     * @return
     */
    @Override
    public ResponseResult reviewUser(User user1) {
        //1.根据id查询用户
        User user = userService.findById(user1.getId());
        List<UserReport> list = userReportService.findByUserid(user.getId());
        for (UserReport userReport : list) {
            userReport.setReviewState(1);
            userReportRepository.save(userReport);
            System.out.println(userReport);
            UserViolation UserViolation = new UserViolation();
            UserViolation.setUser(userReport.getUser());
            UserViolation.setReason(userReport.getReportReason());
            Date date = new Date();
            UserViolation.setEndTime(new Timestamp(date.getTime()));
            userReport.getUser().setUserState(Const.USER_NORMAL_USER);
            userViolationService.add(UserViolation);
        }
        user.setUserState(Const.USER_BANNED);
        user.setNormalDate(user1.getNormalDate());
        userRepository.save(user);
        return new ResponseResult(AdminCode.ADMIN_ALLOW_REVIEW);
    }
    /**
     * 对新闻进行下架操作
     * @param id
     * @param offReason
     * @return
     */
    @Override
    public ResponseResult reviewNewsOff(String id,String offReason) {
        News news = newsService.findById(id);
        news.setNewsState(Const.NEWS_OFF);
        news.setFailureReason(offReason);
        newsRepository.save(news);
        return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWSEXISTENCE);
    }

    /**
     * 对新闻进行解除下架操作
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewNewsOn(String id) {
        News news = newsService.findById(id);
        news.setNewsState(Const.NEWS_PUBLISH);
        newsRepository.save(news);
        return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_NEWEXISTENCE);
    }

    /**
     * 对用户进行禁言操作
     * @param id
     * @param offReason
     * @param normalDate
     * @return
     */
    @Override
    public ResponseResult reviewUserOff(String id,String offReason,String normalDate) {
        User user = userService.findById(id);
        user.setUserState(Const.USER_BANNED);
        String str = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        try {
            Date date = sdf.parse(normalDate);
            user.setNormalDate(new Timestamp(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userRepository.save(user);
        return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_USEREXISTENCE);
    }

    /**
     * 对用户进行解除禁言操作
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserOn(String id) {
        User user = userService.findById(id);
        user.setNormalDate(null);
        user.setUserState(Const.USER_NORMAL_USER);
        userRepository.save(user);
        return new ResponseResult(AdminCode.ADMIN_ALLOW_USEREXISTENCE);
    }

    /**
     * 对用户实名认证处理审核通过
     * @param id
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserVerifiedOn(String id) {
        UserVerified userVerified = userVerifiedService.findById(id);
        userVerified.setReviewState(1);
        userVerified.getUser().setIsCertified(1);
        userVerifiedRepository.save(userVerified);
        return new ResponseResult(AdminCode.ADMIN_ALLOW_USER);
    }

    /**
     * 对用户实名认证处理审核不通过
     * @param id
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserVerifiedOff(String id,String offReason) {
        UserVerified userVerified = userVerifiedService.findById(id);
        userVerified.setReviewState(Const.USER_VERIFIED_FAIL);
        userVerified.setFailureReason(offReason);
        userVerifiedRepository.save(userVerified);
        return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_USER);
    }


    /**
     * 审核用户申请为新闻发布者通过
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserBecomePublishOn(String id) {
        UserApplyToNewsMaker userApplyToNewsMaker = userApplyToNewsMakerService.findById(id);
        userApplyToNewsMaker.setReviewState(Const.PUBLISH);
        userApplyToNewsMakerRepository.save(userApplyToNewsMaker);
        return new ResponseResult(AdminCode.ADMIN_ALLOW_USERPUBLISH);
    }

    /**
     * 审核用户申请为新闻发布者不通过
     * @param id
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserBecomePublishOff(String id,String offReason) {
        UserApplyToNewsMaker userApplyToNewsMaker = userApplyToNewsMakerService.findById(id);
        userApplyToNewsMaker.setReviewState(Const.PUBLISH_FAIL);
        userApplyToNewsMaker.setFailureReason(offReason);
        userApplyToNewsMakerRepository.save(userApplyToNewsMaker);
        return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_USERPUBLISH);
    }

    /**
     * 管理员新闻发布者(将新闻发布者进行降级）
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserBecomeUser(String id) {
        User user = userService.findById(id);
        user.setUserState(Const.USER_NORMAL_USER);
        return new ResponseResult(AdminCode.ADMIN_ALLOW_BECOMEUSER);
    }

    /**
     * 管理管理员
     * @param id
     * @param power
     * @return
     */
    @Override
    public ResponseResult ManagementAdmin(String id,  Integer power) {
        Optional<Admin> optional = adminRepository.findById(id);
        if(optional.isPresent()){
            Admin admin = optional.get();
            admin.setPower(power);
            adminRepository.save(admin);
            return new ResponseResult(AdminCode.ADMIN_BECOMEUSER);
        }
        return new ResponseResult(AdminCode.ADMIN_NOT_BECOMEUSER);
    }

}
