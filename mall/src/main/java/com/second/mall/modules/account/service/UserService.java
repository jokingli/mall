package com.second.mall.modules.account.service;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.common.entity.ResultEntity;


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

    ResultEntity<User> register(User user);
}
