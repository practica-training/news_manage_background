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
    AdminManagementLogRepository adminManagementLogRepository;
    @Autowired
    UserManagementLogRepository userManagementLogRepository;
    @Autowired
    NewsManagementLogRepository newsManagementLogRepository;


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
     * 忽略新闻举报
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewNewsMiss(String id) {
        //1.根据id查询新闻
        Optional<News> optional = newsRepository.findById(id);
        if(optional.isPresent()){
            News news = optional.get();

            NewsManagementLog newsManagementLog = new NewsManagementLog();
            newsManagementLog.setNews(news);
            newsManagementLog.setOperationalContent("新闻id为" + news.getId() + "忽略新闻举报成功");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            newsManagementLog.setProcessingTime(Timestamp.valueOf(format));
            newsManagementLogRepository.save(newsManagementLog);

            List<NewsReport> list = newsReportService.findByNewsid(id);
            for (NewsReport newsReport : list) {
                newsReport.setReviewState(1);
                newsReportService.updateById(newsReport.getId(),newsReport);
            }
            return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWS_MISS);
        }
        return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWS_MISS_FAIL);
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
        Optional<News> optional = newsRepository.findById(id);
        if(optional.isPresent()){
            News news = optional.get();

            NewsManagementLog newsManagementLog = new NewsManagementLog();
            newsManagementLog.setNews(news);
            newsManagementLog.setOperationalContent("新闻id为" + news.getId() + "审核新闻举报成功");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            newsManagementLog.setProcessingTime(Timestamp.valueOf(format));
            newsManagementLogRepository.save(newsManagementLog);

            List<NewsReport> list = newsReportService.findByNewsid(id);
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
            return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWS);
        }
        return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWS_FAIL);
    }

    /**
     * 审核新闻发布成功
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewNewsPublishOn(String id) {
        Optional<News> optional = newsRepository.findById(id);

        if(optional.isPresent()) {
            News news = optional.get();

            NewsManagementLog newsManagementLog = new NewsManagementLog();
            newsManagementLog.setNews(news);
            newsManagementLog.setOperationalContent("新闻id为" + news.getId() + "审核发布成功");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            newsManagementLog.setProcessingTime(Timestamp.valueOf(format));
            newsManagementLogRepository.save(newsManagementLog);

            news.setNewsState(3);
            Date date = new Date();
            news.setPublishTime(new Timestamp(date.getTime()));
            newsRepository.save(news);
            return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWSPUBLISH);
        }
        return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWSPUBLISH_FAIL);
    }

    /**
     * 审核新闻发布失败
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewNewsPublishOff(String id,String offReason) {

        Optional<News> optional = newsRepository.findById(id);

        if(optional.isPresent()) {
            News news = optional.get();

            NewsManagementLog newsManagementLog = new NewsManagementLog();
            newsManagementLog.setNews(news);
            newsManagementLog.setOperationalContent("新闻id为" + news.getId() + "审核发布失败");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            newsManagementLog.setProcessingTime(Timestamp.valueOf(format));
            newsManagementLogRepository.save(newsManagementLog);

            news.setNewsState(Const.NEWS_AUDIT_FAILURE);
            news.setFailureReason(offReason);
            newsRepository.save(news);
            return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_NEWSPUBLISH);
        }
        return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_NEWSPUBLISH_FAIL);

    }


    /**
     * 对新闻进行下架操作
     * @param id
     * @param offReason
     * @return
     */
    @Override
    public ResponseResult reviewNewsOff(String id,String offReason) {
        Optional<News> optional = newsRepository.findById(id);

        if(optional.isPresent()){
            News news = optional.get();

            NewsManagementLog newsManagementLog = new NewsManagementLog();
            newsManagementLog.setNews(news);
            newsManagementLog.setOperationalContent("将新闻id为"+news.getId()+"下架");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            newsManagementLog.setProcessingTime(Timestamp.valueOf(format));
            newsManagementLogRepository.save(newsManagementLog);

            news.setNewsState(Const.NEWS_OFF);
            news.setFailureReason(offReason);
            newsRepository.save(news);
            return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWSEXISTENCE);
        }
        return new ResponseResult(AdminCode.ADMIN_ALLOW_NEWSEXISTENCE_FAIL);
    }

    /**
     * 对新闻进行解除下架操作
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewNewsOn(String id) {
        Optional<News> optional = newsRepository.findById(id);
        if(optional.isPresent()){
            News news = optional.get();

            NewsManagementLog newsManagementLog = new NewsManagementLog();
            newsManagementLog.setNews(news);
            newsManagementLog.setOperationalContent("将新闻id为"+news.getId()+"解除下架");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            newsManagementLog.setProcessingTime(Timestamp.valueOf(format));
            newsManagementLogRepository.save(newsManagementLog);

            news.setNewsState(Const.NEWS_PUBLISH);
            newsRepository.save(news);
            return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_NEWEXISTENCE);
        }
        return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_NEWEXISTENCE_FAIL);
    }


   /**
     * 忽略用户举报
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserMiss(String id) {
        //1.根据id查询用户
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()){
            User user = optional.get();

            UserManagementLog userManagementLog = new UserManagementLog();
            userManagementLog.setOperationalContent("对普通用户"+user.getUserName()+"忽略举报");
            userManagementLog.setUser(user);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            userManagementLog.setProcessingTime(Timestamp.valueOf(format));
            userManagementLogRepository.save(userManagementLog);

            List<UserReport> list = userReportService.findByUserid(id);
            for (UserReport userReport : list) {
                userReport.setReviewState(1);
                userReportRepository.save(userReport);
            }
            return new ResponseResult(AdminCode.ADMIN_ALLOW);
        }
        return new ResponseResult(AdminCode.ADMIN_ALLOW_FAIL);
    }

    /**
     * 审核用户举报  惩罚用户
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUser(String id,String normalDate) {
        //1.根据id查询用户
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()){
            User user = optional.get();

            UserManagementLog userManagementLog = new UserManagementLog();
            userManagementLog.setOperationalContent("对普通用户"+user.getUserName()+"进行违规惩罚");
            userManagementLog.setUser(user);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            userManagementLog.setProcessingTime(Timestamp.valueOf(format));
            userManagementLogRepository.save(userManagementLog);

            List<UserReport> list = userReportService.findByUserid(id);
            for (UserReport userReport : list) {
                userReport.setReviewState(1);
                userReportRepository.save(userReport);
                UserViolation UserViolation = new UserViolation();
                UserViolation.setUser(userReport.getUser());
                UserViolation.setReason(userReport.getReportReason());
                Date date = new Date();
                UserViolation.setEndTime(new Timestamp(date.getTime()));
                userReport.getUser().setUserState(Const.USER_NORMAL_USER);
                userViolationService.add(UserViolation);
            }
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
            return new ResponseResult(AdminCode.ADMIN_ALLOW_REVIEW);
        }
        return new ResponseResult(AdminCode.ADMIN_ALLOW_REVIEW_FAIL);
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

        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            User user = optional.get();

            UserManagementLog userManagementLog = new UserManagementLog();
            userManagementLog.setOperationalContent("普通用户"+user.getUserName()+"进行禁言");
            userManagementLog.setUser(user);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            userManagementLog.setProcessingTime(Timestamp.valueOf(format));
            userManagementLogRepository.save(userManagementLog);


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
        return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_USEREXISTENCE_FAIL);
    }

    /**
     * 对用户进行解除禁言操作
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserOn(String id) {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            User user = optional.get();

            UserManagementLog userManagementLog = new UserManagementLog();
            userManagementLog.setOperationalContent("普通用户"+user.getUserName()+"解除禁言");
            userManagementLog.setUser(user);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            userManagementLog.setProcessingTime(Timestamp.valueOf(format));
            userManagementLogRepository.save(userManagementLog);


            user.setNormalDate(null);
            user.setUserState(Const.USER_NORMAL_USER);
            userRepository.save(user);
            return new ResponseResult(AdminCode.ADMIN_ALLOW_USEREXISTENCE);
        }

        return new ResponseResult(AdminCode.ADMIN_ALLOW_USEREXISTENCE_FAIL);
    }

    /**
     * 对用户实名认证处理审核通过
     * @param id
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserVerifiedOn(String id) {
        Optional<UserVerified> optional = userVerifiedRepository.findById(id);
        if(optional.isPresent()){
            UserVerified userVerified = optional.get();
            //记录管理员管理新闻发布者日志

            UserManagementLog userManagementLog = new UserManagementLog();
            userManagementLog.setOperationalContent("普通用户"+userVerified.getUser().getUserName()+"用户实名认证处理审核通过");
            userManagementLog.setUser(userVerified.getUser());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            userManagementLog.setProcessingTime(Timestamp.valueOf(format));
            userManagementLogRepository.save(userManagementLog);

            userVerified.setReviewState(1);
            userVerified.getUser().setIsCertified(1);
            userVerifiedRepository.save(userVerified);
            return new ResponseResult(AdminCode.ADMIN_ALLOW_USER);
        }
        return new ResponseResult(AdminCode.ADMIN_ALLOW_USER_FAIL);
    }

    /**
     * 对用户实名认证处理审核不通过
     * @param id
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserVerifiedOff(String id,String offReason) {
        Optional<UserVerified> optional = userVerifiedRepository.findById(id);
        if(optional.isPresent()){
            UserVerified userVerified = optional.get();
            //记录管理员管理新闻发布者日志

            UserManagementLog userManagementLog = new UserManagementLog();
            userManagementLog.setOperationalContent("普通用户"+userVerified.getUser().getUserName()+"用户实名认证处理审核不通过");
            userManagementLog.setUser(userVerified.getUser());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            userManagementLog.setProcessingTime(Timestamp.valueOf(format));
            userManagementLogRepository.save(userManagementLog);

            userVerified.setReviewState(Const.USER_VERIFIED_FAIL);
            userVerified.setFailureReason(offReason);
            userVerifiedRepository.save(userVerified);
            return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_USER);
        }
        return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_USER_FAIL);
    }


    /**
     * 审核用户申请为新闻发布者通过
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserBecomePublishOn(String id) {
        Optional<UserApplyToNewsMaker> optional = userApplyToNewsMakerRepository.findById(id);
        if(optional.isPresent()){
            UserApplyToNewsMaker userApplyToNewsMaker = optional.get();
            //记录管理员管理新闻发布者日志

            UserManagementLog userManagementLog = new UserManagementLog();
            userManagementLog.setOperationalContent("普通用户"+userApplyToNewsMaker.getUser().getUserName()+"申请为新闻发布者通过");
            userManagementLog.setUser(userApplyToNewsMaker.getUser());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            userManagementLog.setProcessingTime(Timestamp.valueOf(format));
            userManagementLogRepository.save(userManagementLog);

            userApplyToNewsMaker.setReviewState(Const.PUBLISH);
            userApplyToNewsMakerRepository.save(userApplyToNewsMaker);
            return new ResponseResult(AdminCode.ADMIN_ALLOW_USERPUBLISH);
        }
        return new ResponseResult(AdminCode.ADMIN_ALLOW_USERPUBLISH_FAIL);
    }

    /**
     * 审核用户申请为新闻发布者不通过
     * @param id
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserBecomePublishOff(String id,String offReason) {

        Optional<UserApplyToNewsMaker> optional = userApplyToNewsMakerRepository.findById(id);
        if(optional.isPresent()){
            UserApplyToNewsMaker userApplyToNewsMaker = optional.get();
            //记录管理员管理新闻发布者日志
            UserManagementLog userManagementLog = new UserManagementLog();
            userManagementLog.setOperationalContent("普通用户"+userApplyToNewsMaker.getUser().getUserName()+"申请为新闻发布者不通过");
            userManagementLog.setUser(userApplyToNewsMaker.getUser());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            userManagementLog.setProcessingTime(Timestamp.valueOf(format));
            userManagementLogRepository.save(userManagementLog);

            userApplyToNewsMaker.setReviewState(Const.PUBLISH_FAIL);
            userApplyToNewsMaker.setFailureReason(offReason);
            userApplyToNewsMakerRepository.save(userApplyToNewsMaker);
            return new ResponseResult(AdminCode.ADMIN_NOT_ALLOW_USERPUBLISH);
        }
        return new ResponseResult(AdminCode.ADMIN_ALLOW_USERPUBLISH_FAIL);

    }

    /**
     * 管理员新闻发布者(将新闻发布者进行降级）
     * @param id
     * @return
     */
    @Override
    public ResponseResult reviewUserBecomeUser(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()){
            User user = optional.get();
            //记录管理员管理新闻发布者日志
            UserManagementLog userManagementLog = new UserManagementLog();
            userManagementLog.setOperationalContent("将新闻发布者"+user.getUserName()+"降级为普通用户");
            userManagementLog.setUser(user);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            userManagementLog.setProcessingTime(Timestamp.valueOf(format));
            userManagementLogRepository.save(userManagementLog);
            user.setUserState(Const.USER_NORMAL_USER);
            return new ResponseResult(AdminCode.ADMIN_ALLOW_BECOMEUSER);
        }
        return new ResponseResult(AdminCode.ADMIN_ALLOW_BECOMEUSER_FAIL);
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
            //记录超级管理员管理管理员日志
            AdminManagementLog adminManagementLog  = new AdminManagementLog();
            adminManagementLog.setOperationalContent("将管理员"+admin.getAdminName()+"等级从"+admin.getPower()+"变为"+power);
            adminManagementLog.setAdmin(admin);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df.format(new Date());
            adminManagementLog.setProcessingTime(Timestamp.valueOf(format));
            admin.setPower(power);
            adminManagementLogRepository.save(adminManagementLog);
            adminRepository.save(admin);
            return new ResponseResult(AdminCode.ADMIN_BECOMEUSER);
        }
        return new ResponseResult(AdminCode.ADMIN_NOT_BECOMEUSER);
    }

}
