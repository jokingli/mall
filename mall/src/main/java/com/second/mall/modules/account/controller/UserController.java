package com.second.mall.modules.account.controller;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.account.service.UserService;
import com.second.mall.modules.common.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName UserController
 * @Author icy
 * @Data 2021/1/4 11:18
 * @Version v1.0
 **/
@Controller
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1/api/user
     * {"name":"冰雨","password":"123456","tel":"12345678999","address":"china","sex":"男"}
     */
    @PostMapping(value = "/user", consumes = "application/json")
    @ResponseBody
    public ResultEntity<User> insertUser(@RequestBody User user){
        return userService.insertUser(user);
    }
}
