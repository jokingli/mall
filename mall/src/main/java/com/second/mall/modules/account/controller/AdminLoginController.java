package com.second.mall.modules.account.controller;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.account.service.UserService;
import com.second.mall.modules.common.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName LoginController
 * @Author icy
 * @Data 2021/1/4 11:12
 * @Version v1.0
 **/
@Controller
@RequestMapping(value = "/account")
public class AdminLoginController {

    @Autowired(required = false)
    UserService userService;

    //进入管理员登录页面
    @GetMapping(value = "/login")
    public String login(ModelMap modelMap){
        modelMap.put("template","account/adminLogin");
        return "managerIndexSimple";
    }

    //进行管理员密码登录
    @PostMapping(value = "/pwdLogin", consumes = "application/json")
    @ResponseBody
    public ResultEntity<User> pwdLogin(@RequestBody User user) {
        return userService.login(user);
    }


    //进入管理员主页面
    @GetMapping(value = "/index")
    //登录成功跳转到主页面
    public String adminIndex(ModelMap modelMap){
//        modelMap.put("template", "common/dashboard");
        modelMap.put("template","account/adminIndex");
        return "managerIndexSimple";
    }


    //进入管理员注册页面
    @GetMapping(value = "/register")
    public String register(ModelMap modelMap) {
        modelMap.put("template","account/adminRegister");
        return "managerIndexSimple";
    }

    //开始进行管理员页面注册
    @ResponseBody
    @PostMapping(value = "/user")
    public ResultEntity<User> user(@RequestBody User user){
        return userService.register(user);
    }

    @RequestMapping("/logout")
    public String logout(ModelMap modelMap) {
        userService.logout();
        modelMap.addAttribute("template", "account/adminLogin");
        return "managerIndexSimple";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "managerIndex";
    }


}

