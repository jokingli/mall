package com.second.mall.modules.shopping.controller;

import com.second.mall.modules.shopping.entity.ShoppingCar;
import com.second.mall.modules.shopping.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ShoppingCarController")
public class ShoppingCarController {
    @Autowired
    ShoppingCarService shoppingCarService;
    /**
     *
     * 添加商品到用户购物车
     * @return
     */
    @RequestMapping(value = "/userAddShoppingCar")
    public String userAddShoppingCar(ShoppingCar shoppingCar){


        ShoppingCar shoppingCar1=shoppingCarService.userAddShoppingCar(shoppingCar);


    return  null;


    }





}
