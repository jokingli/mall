package com.second.mall.modules.account.service.impl;

import com.second.mall.modules.account.dao.UserDao;
import com.second.mall.modules.account.dao.UserRoleDao;
import com.second.mall.modules.account.entity.Role;
import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.account.service.UserService;
import com.second.mall.modules.common.entity.ResultEntity;

//import com.second.mall.utils.MD5Util;
import com.second.mall.utils.MdFive;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author icy
 * @Data 2021/1/4 11:27
 * @Version v1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
     UserDao userDao;
    @Autowired
     UserRoleDao userRoleDao;

//    @Autowired
//    private ResourceConfigBean resourceConfigBean;

    @Override
    @Transactional
    public ResultEntity<User> insertUser(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setState(1);
        user.setDel(1);
        //将输入的密码进行MD5加密后放入数据库
        user.setPassword(MdFive.getMD5(user.getPassword()));
        System.err.println(user);
        userDao.insertUser(user);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "Insert success", user);
    }


    // 密码验证登录
    @Override
    public ResultEntity<User> login(User user) {
        try {
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken(user.getUserName(), MdFive.getMD5(user.getPassword()));

            subject.login(usernamePasswordToken);
            subject.checkRoles();

            Session session = subject.getSession();
            User userTemp = (User) subject.getPrincipal();
            session.setAttribute("userId", userTemp.getUserId());
            session.setAttribute("userName", userTemp.getUserName());

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status, "用户名或者密码错误");
        }
        return new ResultEntity<User>(ResultEntity.ResultStatus.SUCCESS.status, "登录成功", user);
    }


    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    //账号注册
    @Override
    @Transactional
    public ResultEntity<User> register(User user) {
        System.err.println(user.getUserName());
        System.err.println(user.getPassword());
        if (user.getUserName() == "" || user.getPassword() == "" ){
            return new ResultEntity<User>(ResultEntity.ResultStatus.FAILED.status, "账号名或密码不能为空！");
        }

        User userTemp = getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
            return new ResultEntity<User>(ResultEntity.ResultStatus.FAILED.status, "账号名重复！");
        }

        user.setPassword(MdFive.getMD5(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        user.setState(1);
        user.setDel(1);

        return new ResultEntity<User>(ResultEntity.ResultStatus.SUCCESS.status, "注册账号成功，返回登录页！");

////         管理员编辑用户信息时，只修改用户角色
//        if (user.getUserId() > 0) {
////            return new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status, "注册失败", user);
////			userDao.updateUser(user);
//            userRoleDao.deletUserRoleByUserId(user.getUserId());
//        } else {
//            userDao.insertUser(user);
//            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status, "注册成功", user);
//        }
//
//        List<Role> roles = user.getRoleList();
//        if (roles != null && roles.size() > 0) {
//            for (Role role : roles) {
//                userRoleDao.addUserRole(user.getUserId(), role.getRoleId());
//            }
//        }
//        return new ResultEntity<User>(ResultEntity.ResultStatus.SUCCESS.status, "注册账号成功，返回登录页！",user);

    }


    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession();
        session.removeAttribute("userId");
    }
}