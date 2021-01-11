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
@RequestMapping(value = "/mall")
public class MallController {


    @RequestMapping(value = "/comments")
    public String comments(ModelMap modelMap){
        modelMap.put("template","shopping/comment");
        return "managerIndex";
    }

    @RequestMapping(value = "/product")
    public String product(ModelMap modelMap){
        modelMap.put("template","shopping/product");
        return "mallIndex";
    }


    /**
     * 订单主页
     */
    @RequestMapping(value = "/indent")
    public String indent(ModelMap modelMap){
        modelMap.put("template","mall/indent");
        return "mallIndex";
    }


    /**
     * 地址显示
     */
    @RequestMapping(value = "/address")
    public String address(ModelMap modelMap){
        modelMap.put("template","mall/address");
        return "mallIndex";
    }

}
