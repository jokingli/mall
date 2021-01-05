package com.second.mall.modules.account.service;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.common.entity.ResultEntity;

import java.util.HashMap;

/**
 * @ClassName UserService
 * @Author icy
 * @Data 2021/1/4 11:26
 * @Version v1.0
 **/
public interface UserService {
    ResultEntity<User> insertUser(User user);

    //管理员密码登录
    HashMap<String, Object> pwdLogin(User user);
}
