package com.second.mall.modules.account.controller;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.account.service.RoleService;
import com.second.mall.modules.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @ClassName LoginController
 * @Author icy
 * @Data 2021/1/4 11:12
 * @Version v1.0
 **/
@Controller
@RequestMapping(value = "/account")
public class AdminLoginController {

//    @Autowired
//    SmsSend smsSend;

    @Autowired(required = false)
    UserService userService;

    @Autowired(required = false)
    RoleService roleService;

//    @Autowired(required = false)
//    MdFive mdFive;

    //进入管理员登录页面
    @RequestMapping(value = "/login")
    public String adminLogin(ModelMap modelMap){
        System.err.println("进入管理员登录页面");
        modelMap.put("template","account/adminLogin");
        return "managerIndexSimple";
    }

    //进入管理员主页面
    @RequestMapping("/index")
    //登录成功跳转到主页面
    public String adminIndex(){
        System.err.println("开始跳转主页面");
        return "/account/adminIndex";
    }

    //管理员密码登录
    @ResponseBody
    @RequestMapping(value = "/pwdLogin")
    public HashMap<String,Object> pwdLogin(User user){
        System.err.println("开始进行密码登录");
        System.err.println(user.getUserName());
        System.err.println(user.getPassword());
        System.err.println(user);

        return userService.pwdLogin(user);
    }

}
