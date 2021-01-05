package com.second.mall.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName LoginController
 * @Author icy
 * @Data 2021/1/4 11:12
 * @Version v1.0
 **/
@Controller
@RequestMapping(value = "/account")
public class LoginController {

    @RequestMapping(value = "/login")
    public String login(ModelMap modelMap){
        modelMap.put("template","account/login");
        return "managerIndexSimple";
    }
}
