package com.second.mall.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yqs
 * @Comments
 * @Date 2021/1/5 14:33
 **/
@Controller
@RequestMapping(value = "/account")
public class UserLoginController {
    @RequestMapping(value = "/userLogin")
    public String login(ModelMap modelMap){
        modelMap.put("template","account/userLogin");
//        return "managerIndexSimple";
            return "managerIndex";

    }
}
