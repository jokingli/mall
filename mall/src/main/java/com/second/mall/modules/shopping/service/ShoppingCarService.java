package com.second.mall.modules.shopping.service;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ShoppingCar;

import java.util.List;

public interface ShoppingCarService {

    ResultEntity<ShoppingCar> userAddShoppingCar(ShoppingCar shoppingCar);

    //查询用户购物车信息
    List<Product> selectShopingCar(int userId);

    //批量删除购物车
    ResultEntity<ShoppingCar> deleteShoppingCarByProductIdList(List<Integer> productIdList,int userId);

    ResultEntity<ShoppingCar> clearShopingCarByUserId(int userId);

    ResultEntity<ShoppingCar> deletOneProduct(int userId,int poductId);
}
