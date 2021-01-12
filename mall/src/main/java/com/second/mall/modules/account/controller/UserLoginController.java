package com.second.mall.modules.account.controller;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.account.service.UserService;
import com.second.mall.modules.common.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author yqs
 * @Comments
 * @Date 2021/1/5 14:33
 **/
@Controller
@RequestMapping(value = "/shopping")
public class UserLoginController {

    @Autowired(required = false)
    UserService userService;

    //进入用户登录页面
    @RequestMapping(value = "/login")
    public String userLogin(ModelMap modelMap){
        System.err.println("进入用户登录页面");
        modelMap.put("template","account/userLogin");
        return "mallIndexSimple";
    }

    //进入用户主页面
    @RequestMapping("/index")
    //登录成功跳转到主页面
    public String userIndex(ModelMap modelMap){
        modelMap.put("template","account/userIndex");
        return "mallIndex";
    }

    //用户密码登录
    @ResponseBody
    @RequestMapping(value = "/pwdLogin")
    public ResultEntity<User> pwdLogin(User user){
        System.err.println("开始进行用户密码登录");
        System.err.println(user.getUserName());
        System.err.println(user.getPassword());
        System.err.println(user);

        return userService.login(user);
    }
}
