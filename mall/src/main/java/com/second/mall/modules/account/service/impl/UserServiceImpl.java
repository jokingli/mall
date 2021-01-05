package com.second.mall.modules.account.service.impl;

import com.second.mall.modules.account.dao.UserDao;
import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.account.service.UserService;
import com.second.mall.modules.common.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * @ClassName UserServiceImpl
 * @Author icy
 * @Data 2021/1/4 11:27
 * @Version v1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public ResultEntity<User> insertUser(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setState(1);
        user.setDel(1);
        System.out.println(user);
        userDao.insertUser(user);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "Insert success", user);
    }
}
