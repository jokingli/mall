package com.second.mall.modules.account.controller;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.account.service.UserService;
import com.second.mall.modules.common.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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

    //进入商城登录页面
    @RequestMapping(value = "/login")
    public String login(ModelMap modelMap){
        System.err.println("进入用户登录页面");
        modelMap.put("template","account/userLogin");
        return "managerIndexSimple";
    }

    //商城用户密码登录
    //添加以后变成接口，返回字符串类型
    @ResponseBody

    @RequestMapping(value = "/pwdLogin")
    public ResultEntity<User> pwdLogin(@RequestBody User user){
        System.err.println("开始进行用户密码登录");
        System.err.println(user.getUserName());
        System.err.println(user.getPassword());
        System.err.println(user);

        return userService.login(user);
    }

    //进入商城主页面
    @RequestMapping("/index")
    //登录成功跳转到主页面
    public String userIndex(ModelMap modelMap){
        System.err.println("开始跳转用户主页面");
        modelMap.put("template","account/userIndex");
        return "managerIndexSimple";
    }

    //进入商城用户注册页面
    @RequestMapping(value = "/register")
    public String register(ModelMap modelMap) {
        System.err.println("进入用户密码注册页面");
        modelMap.put("template","account/userRegister");
        return "managerIndexSimple";
    }

    //开始进行用户密码注册
    @ResponseBody
    @RequestMapping(value = "/user")
    public ResultEntity<User> user(@RequestBody User user){
        System.err.println("开始进行用户密码注册");
        System.err.println(user.getUserName());
        System.err.println(user.getPassword());
        System.err.println(user);

        return userService.register(user);
    }




    @RequestMapping("/logout")
    public String logout(ModelMap modelMap) {
        userService.logout();
        modelMap.addAttribute("template", "account/userLogin");
        return "managerIndexSimple";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "managerIndex";
    }

}
