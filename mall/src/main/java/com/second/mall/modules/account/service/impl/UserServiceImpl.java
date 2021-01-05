package com.second.mall.modules.account.service.impl;

import com.second.mall.modules.account.dao.UserDao;
import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.account.service.UserService;
import com.second.mall.modules.common.entity.ResultEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;

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

    @Override
    public HashMap<String, Object> pwdLogin(User user) {
        System.err.println("进行密码匹配登录");
        HashMap<String, Object> map = new HashMap<String, Object>();
        try{
            //1 创建用户token
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
            //2 创建一个shiro用户
            Subject subject = SecurityUtils.getSubject();
            //3 登录
            subject.login(token);
            System.err.println("密码正确，登录成功");
            map.put("info","登录成功");
        }catch (UnknownAccountException e){
            e.printStackTrace();
            map.put("info","用户名输入错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            map.put("info","密码输入错误");
        }
        return map;
    }
}
