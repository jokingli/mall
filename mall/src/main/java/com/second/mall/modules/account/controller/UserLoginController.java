package com.second.mall.modules.account.controller;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.account.service.UserService;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


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

    @Autowired
    ProductService productService;

    //进入商城登录页面
    @RequestMapping(value = "/login")
    public String login(ModelMap modelMap){
        modelMap.put("template","account/userLogin");
        return "managerIndexSimple";
    }

    //商城用户密码登录
    //添加以后变成接口，返回字符串类型
    @ResponseBody
    @RequestMapping(value = "/pwdLogin", consumes="application/json")
    public ResultEntity<User> pwdLogin(@RequestBody User user){
        return userService.login(user);
    }

    //进入商城主页面
    @RequestMapping("/index")
    //登录成功跳转到主页面
    public String userIndex(ModelMap modelMap){
        modelMap.put("template","index");
        return "managerIndexSimple";
    }

    //进入商城用户注册页面
    @RequestMapping(value = "/register")
    public String register(ModelMap modelMap) {
        modelMap.put("template","account/userRegister");
        return "managerIndexSimple";
    }

    //开始进行用户密码注册
    @ResponseBody
    @RequestMapping(value = "/user", consumes = "application/json")
    public ResultEntity<User> user(@RequestBody User user){
        return userService.register(user);
    }

    @RequestMapping("/logout")
    public String logout(ModelMap modelMap) {
        userService.logout();
//        List<Product> productList = productService.selectProduct();
//        request.setAttribute("productList", productList);
//        request.setAttribute("template", "mall/index");
        modelMap.addAttribute("template", "mall/index");
        return "mallIndex";

    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "managerIndex";
    }

}
