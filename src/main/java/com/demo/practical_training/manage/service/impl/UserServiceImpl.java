package com.demo.practical_training.manage.service.impl;

import com.demo.practical_training.common.response.CommonCode;
import com.demo.practical_training.common.response.QueryResponseResult;
import com.demo.practical_training.common.response.QueryResult;
import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.web.STablePageRequest;
import com.demo.practical_training.entity.User;
import com.demo.practical_training.dao.UserRepository;
import com.demo.practical_training.manage.service.UserService;
import com.demo.practical_training.model.request.QueryUserRequest;
import com.demo.practical_training.model.response.UserResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional()
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository UserRepository;
    /**
     * 分页和排序加动态查询用户页面
     *
     * @param pageRequest
     * @param queryUserRequest
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public QueryResponseResult findList(STablePageRequest pageRequest, QueryUserRequest queryUserRequest) {
        //条件匹配器         
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        //模糊匹配别名
        exampleMatcher = exampleMatcher.withMatcher("UserTitle", ExampleMatcher.GenericPropertyMatchers.contains());
        //创建条件值对象
        User User = new User();
        //判断用户名是否为空
        if (StringUtils.isNotEmpty(queryUserRequest.getUserID())) {
            User.setUserID(queryUserRequest.getUserID());
        }
        //判断用户id是否为空
        if (StringUtils.isNotEmpty(queryUserRequest.getUserName())) {
            User.setUserName(queryUserRequest.getUserName());
        }
        //创建条件实例对象
        Example<User> example = Example.of(User, exampleMatcher);

        //根据分页对象和条件实例对象查询数据
        Page<User> all = UserRepository.findAll(example, pageRequest.getPageable());
        //新建QueryResult<T> 对象
        QueryResult<User> UserQueryResult = new QueryResult<>();
        //分别给QueryResult<T> 对象中的list集合total赋值
        UserQueryResult.setList(all.getContent());
        UserQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, UserQueryResult);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    public UserResult add(User user) {
        User user1 = UserRepository.save(user);
        return new UserResult(CommonCode.SUCCESS,user1);
    }

    /**
     * 根据id修改用户
     * @param id
     * @param user
     * @return
     */
    @Override
    public UserResult updateById(String id, User user) {
        //根据Id查询用户
        User user1 = this.findById(id);
        //若存在，则调用set方法更新数据，并保存
        if(user1!=null){
            //设置用户名
            user1.setUserName(user.getUserName());
            //设置性别 0女 1男
            user1.setUserSex(user.getUserSex());
            //设置用户密码
            user1.setUserPassword(user.getUserPassword());
            //设置手机号码
            user1.setUserPhone(user.getUserPhone());
            //设置是否实名认证
            user1.setIsCertified(user.getIsCertified());
            //设置违规次数
            user1.setViolationNumber(user.getViolationNumber());
            //设置用户状态 1正常 0已注销 -1违规禁言 -2违规封号
            user1.setUserState(user.getUserState());
            //设置用户头像路径
            user1.setUserAvatar(user.getUserAvatar());
            //设置注册时间
            user.setRegistrationTime(user.getRegistrationTime());
            User user2 = UserRepository.save(user1);
            if(user2!=null){
                return new UserResult(CommonCode.SUCCESS,user2);
            }

        }
        //若不存在，则返回失败
        return new UserResult(CommonCode.FAIL,null);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据Id查询用户
        User user1 = this.findById(id);
        if(user1!=null){
            //若存在，删除用户
            UserRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //若不存在，则返回fail
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public User findById(String id) {
        Optional<User> optional = UserRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}