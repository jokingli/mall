package com.second.mall.modules.shopping.controller;

import com.second.mall.modules.shopping.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/indents")
    public String indents(ModelMap modelMap){
        modelMap.put("template","mall/indents");
        return "mallIndex";
    }

    @RequestMapping(value = "/product")
    public String product(ModelMap modelMap){
        modelMap.put("template","mall/product");
        return "mallIndex";
    }


    /**
     * 订单主页
     */
    @RequestMapping(value = "/indent/{indentCode}")
    public String indent(@PathVariable String indentCode, ModelMap modelMap){
        modelMap.put("indentCode",indentCode);
        modelMap.put("template","mall/indent");
        return "mallIndex";
    }


    /**
     * 地址显示
     */
    @RequestMapping(value = "/address/{userId}")
    public String address(@PathVariable int userId, ModelMap modelMap){
        modelMap.put("userId",userId);
        modelMap.put("template","mall/address");
        return "mallIndex";
    }

    /**
     * 支付
     */
    @RequestMapping(value = "/pay/{indentCode}")
    public String pay(@PathVariable String indentCode,ModelMap modelMap){
        modelMap.put("indentCode",indentCode);
        modelMap.put("template","mall/pay");
        return "mallIndex";
    }

    /**
     * 确认支付
     */
    @RequestMapping(value = "/indentConfirmed/{indentCode}")
    public String indentConfirmed(@PathVariable String indentCode,ModelMap modelMap){
        modelMap.put("indentCode",indentCode);
        modelMap.put("template","mall/indent");
        return "mallIndex";
    }

    @RequestMapping(value = "/comment/{indentItemId}")
    public String comments(@PathVariable String indentItemId,ModelMap modelMap){
        modelMap.put("indentCode",indentItemId);
        modelMap.put("template","mall/comment");
        return "mallIndex";
    }

    /**
     * 127.0.0.1/mall/comment/528569379068055552/3/1
     */
    @GetMapping("/comment/{indentCode}/{productId}/{indentItemId}")
    public String reviewPage(@PathVariable String indentCode, @PathVariable int productId, @PathVariable int indentItemId, ModelMap modelMap) {
        modelMap.put("template", "mall/comment");
        modelMap.put("productId", productId);
        modelMap.put("indentCode", indentCode);
        modelMap.put("indentItemId", indentItemId);
        return "mallIndex";
    }
}
