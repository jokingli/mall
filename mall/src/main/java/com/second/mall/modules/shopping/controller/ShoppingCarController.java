package com.second.mall.modules.shopping.controller;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ShoppingCar;
import com.second.mall.modules.shopping.entity.ShoppingCarItems;
import com.second.mall.modules.shopping.service.ProductService;
import com.second.mall.modules.shopping.service.ShoppingCarService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/shoppingCar")
public class ShoppingCarController {
    @Autowired
    ShoppingCarService shoppingCarService;
    @Autowired
    ProductService productService;

    /**
     *进入购物车页面
     * 127.0.0.1/shoppingCar/enter
     */
    @RequestMapping(value = "/enter")
    public String enter(HttpServletRequest request){
        request.setAttribute("template","/shopping/shoppingCar");
        return "/mallIndex";
    }

    /**
     * 通过用户id显示用户购物车中的商品
     * 127.0.0.1/shoppingCar/show/1
     */
    @GetMapping(value = "/show/{userId}")
    @ResponseBody
    public List<ShoppingCarItems> show(@PathVariable int userId, HttpServletRequest request) {
        //通过用户id得到用户购物车中商品和商品数量在页面上展示出来
        return shoppingCarService.selectShopingCar(userId);
    }

    /**
     * 通过用户id显示用户购物车中的商品
     * 127.0.0.1/shoppingCar/show/1
     */
    @GetMapping(value = "/show/state/{userId}")
    @ResponseBody
    public List<ShoppingCarItems> showInBuy(@PathVariable int userId, HttpServletRequest request) {
        //通过用户id得到用户购物车中商品和商品数量在页面上展示出来
        return shoppingCarService.selectShoppingCarByState(userId);
    }
    /**
     * 添加商品到用户购物车
         * 127.0.0.1/shoppingCar/add
     * {"userId":"1","productId":"2"}
     */
    @PostMapping(value = "/add",consumes = "application/json")
    @ResponseBody
    public ResultEntity<ShoppingCar> userAddShoppingCar(@RequestBody ShoppingCarItems shoppingCarItems) {

        return  shoppingCarService.userAddShoppingCar(shoppingCarItems);
    }


    /**
     * 批量删除购物车对应商品
     * 127.0.0.1/shoppingCar/delete
     * （1，2），1
     */
    @DeleteMapping(value = "/delete", consumes = "application/json")
    @ResponseBody
    public ResultEntity<ShoppingCar> deleteShoppingCar(@RequestBody List<Integer> ids) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        //通过session拿到用户id;
        int userId =user.getUserId();
        return shoppingCarService.deleteShoppingCarByProductIdList(ids,userId);
    }

    /**
     *清空用户的购物车
     * 127.0.0.1/shoppingCar/clear/1
     */
    @DeleteMapping(value = "/clear")
    @ResponseBody
    public ResultEntity<ShoppingCar> clearShoppingCar() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        //通过session拿到用户id;
        int userId =user.getUserId();
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
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        //通过session拿到用户id;
        int userId =user.getUserId();
        return shoppingCarService.deletOneProduct(userId,productId);
    }

    /**
     * 修改商品数量
     */
    @PutMapping(value = "/change",consumes = "application/json")
    @ResponseBody
    public ResultEntity<ShoppingCar> changeNum(@RequestBody ShoppingCarItems shoppingCarItems) {

        return shoppingCarService.changeNum(shoppingCarItems.getProductId(),shoppingCarItems.getNumber());
    }


    /**
     * 添加商品到用户购物车
     * 127.0.0.1/shoppingCar/add
     * {"userId":"1","productId":"2"}
     */
    @PostMapping(value = "/shoppingCars",consumes = "application/json")
    @ResponseBody
    public ResultEntity<Object> userUpdateShoppingCar(@RequestBody List<ShoppingCarItems> shoppingCarItems) {

        return  shoppingCarService.userUpdateShoppingCar(shoppingCarItems);
    }


}
