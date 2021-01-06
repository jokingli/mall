package com.second.mall.modules.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName MallController:站点页面之间跳转
 * @Author icy
 * @Data 2021/1/6 15:12
 * @Version v1.0
 **/
@Controller
@RequestMapping(value = "/shopping")
public class MallController {


    @RequestMapping(value = "/comments")
    public String comments(ModelMap modelMap){
        modelMap.put("template","shopping/comments");
        return "mallIndex";
    }

    @RequestMapping(value = "/product")
    public String product(ModelMap modelMap){
        modelMap.put("template","shopping/product");
        return "mallIndex";
    }
}
