package com.second.mall.modules.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ShoppingController:
 * @Author icy
 * @Data 2021/1/11 9:40
 * @Version v1.0
 **/
@Controller
@RequestMapping(value = "/shopping")
public class ShoppingController {
    /**
     * 所有订单
     */
    @RequestMapping(value = "/indent")
    public String indent(ModelMap modelMap){
        modelMap.put("template","shopping/indent");
        return "managerIndex";
    }
    /**
     * 所有评论
     */
    @RequestMapping(value = "/comment")
    public String comment(ModelMap modelMap){
        modelMap.put("template","shopping/comment");
        return "managerIndex";
    }
}
