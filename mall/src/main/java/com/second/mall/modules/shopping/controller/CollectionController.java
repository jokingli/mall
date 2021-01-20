package com.second.mall.modules.shopping.controller;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Collection;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ShoppingCar;
import com.second.mall.modules.shopping.entity.ShoppingCarItems;
import com.second.mall.modules.shopping.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 收藏控制器
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    CollectionService collectionService;


    /**
     * 进入藏品页面
     * 127.0.0.1/collection/enter
     */
    @RequestMapping(value = "/enter")
    public String enter(HttpServletRequest request){
        request.setAttribute("template","/shopping/collection");
        return "/mallIndex";
    }

    /**
     * 通过用户id显示藏品
     * 127.0.0.1/collection/show/1
     */
    @GetMapping(value = "/show/{userId}")
    @ResponseBody
    public List<Product> show(@PathVariable int userId, HttpServletRequest request) {
        //通过用户id得到收藏在页面上展示出来
        return collectionService.selectCollection(userId);
    }

    /**
     * 添加商品到收藏
     * 127.0.0.1/collection/add
     * {"userId":"1","productId":"2"}
     */
    @PostMapping(value = "/add/{productId}")
    @ResponseBody
    public ResultEntity<ShoppingCar> userAddShoppingCar(@PathVariable int productId) {
        return  collectionService.userAddCollection(productId);
    }

    /**
     * 批量删除收藏对应商品
     * 127.0.0.1/collection/delete
     * （1，2），1
     */
    @DeleteMapping(value = "/delete", consumes = "application/json")
    @ResponseBody
    public ResultEntity<ShoppingCar> deleteShoppingCar(@RequestBody List<Integer> ids) {
        return collectionService.deleteConllectionByProductIdList(ids);
    }

    /**
     *删除用户购物车的某件商品
     * 127.0.0.1/collection/delete-one/1
     * {"userId":"1","productId":"1"}
     */
    @DeleteMapping(value = "/delete-one/{productId}")
    @ResponseBody
    public ResultEntity<ShoppingCar> deletOneProduct(@PathVariable int productId) {
        return collectionService.deletOneProduct(productId);
    }
}