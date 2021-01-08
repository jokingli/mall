package com.second.mall.modules.shopping.controller;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ShoppingCar;
import com.second.mall.modules.shopping.service.ProductService;
import com.second.mall.modules.shopping.service.ShoppingCarService;
import com.second.mall.modules.shopping.vo.Yyy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/shoppingCar")
public class ShoppingCarController {
    @Autowired
    ShoppingCarService shoppingCarService;
    @Autowired
    ProductService productService;

    /**
     * 通过用户id显示用户购物车中的商品
     * 127.0.0.1/shoppingCar/show/1
     */
    @GetMapping(value = "/show/{userId}")
    public String show(@PathVariable int userId, HttpServletRequest request) {

        //通过用户id得到用户购物车中商品id和商品数量，通过商品id得到商品数据，在页面上展示出来
        List<Product> productList=shoppingCarService.selectShopingCar(userId);
        request.setAttribute("productList",productList);

        return "/shopping/shoppingCar";
    }

    /**
     * 添加商品到用户购物车
         * 127.0.0.1/shoppingCar/add
     * {"userId":"1","productId":"2"}
     */
    @PostMapping(value = "/add",consumes = "application/json")
    public ResultEntity<ShoppingCar> userAddShoppingCar(@RequestBody ShoppingCar shoppingCar) {
        return  shoppingCarService.userAddShoppingCar(shoppingCar);
    }

    /**
     * 批量删除购物车对应商品
     * 127.0.0.1/shoppingCar/delete
     * （1，2），1
     */
    @DeleteMapping(value = "/delete", consumes = "application/json")
    @ResponseBody
    public ResultEntity<ShoppingCar> deleteShoppingCar(@RequestBody List<Integer> ids) {
        int userId=1;
        return shoppingCarService.deleteShoppingCarByProductIdList(ids,userId);
    }

    /**
     *清空用户的购物车
     * 127.0.0.1/shoppingCar/clear/1
     */
    @DeleteMapping(value = "/clear")
    public ResultEntity<ShoppingCar> clearShoppingCar() {
        int userId=1;
        return shoppingCarService.clearShopingCarByUserId(userId);
    }

    /**
     *删除用户购物车的某件商品
     * 127.0.0.1/shoppingCar/delete-one/1
     * {"userId":"1","productId":"1"}
     */
    @DeleteMapping(value = "/delete-one/{productId}")
    @ResponseBody
    public ResultEntity<ShoppingCar> deletOneProduct(@PathVariable int productId) {
        int userId=1;
        return shoppingCarService.deletOneProduct(userId,productId);
    }

}
