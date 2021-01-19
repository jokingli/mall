package com.second.mall.modules.account.service;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.common.entity.ResultEntity;

import java.util.List;


/**
 * @ClassName UserService
 * @Author icy
 * @Data 2021/1/4 11:26
 * @Version v1.0
 **/
public interface UserService {
    ResultEntity<User> insertUser(User user);

    void logout();

    //管理员密码登录
    ResultEntity<User> login(User user);

    User getUserByUserName(String userName);

    //注册用户
    ResultEntity<User> register(User user);

    //查询用户
    List<User> select();

//    ResultEntity<User> queryRoleByUserName(User user, HttpServletRequest request);
}
