package com.second.mall.modules.shopping.controller;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ShoppingCar;
import com.second.mall.modules.shopping.service.ProductService;
import com.second.mall.modules.shopping.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
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
    public List<Product> show(@PathVariable int userId) {

        //通过用户id得到用户购物车中商品id和商品数量，通过商品id得到商品数据，在页面上展示出来
        return shoppingCarService.selectShopingCar(userId);
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
    @DeleteMapping(value = "/delete")
    public ResultEntity<ShoppingCar> deleteShoppingCar(List<Integer> productIdList,int userId) {
        return shoppingCarService.deleteShoppingCarByProductIdList(productIdList,userId);
    }

    /**
     *清空用户的购物车
     * 127.0.0.1/shoppingCar/clear/1
     */
    @DeleteMapping(value = "/clear/{userId}")
    public ResultEntity<ShoppingCar> clearShoppingCar(@PathVariable int userId) {
        return shoppingCarService.clearShopingCarByUserId(userId);
    }

    /**
     *删除用户购物车的某件商品
     * 127.0.0.1/shoppingCar/delete-one
     * {"userId":"1","productId":"1"}
     */
    @DeleteMapping(value = "/delete-one",consumes = "application/json")
    public ResultEntity<ShoppingCar> deletOneProduct(@RequestBody ShoppingCar shoppingCar) {
        return shoppingCarService.deletOneProduct(shoppingCar.getUserId(),shoppingCar.getProductId());
    }

}
