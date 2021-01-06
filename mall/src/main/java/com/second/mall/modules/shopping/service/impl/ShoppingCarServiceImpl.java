package com.second.mall.modules.shopping.service.impl;

import com.second.mall.modules.shopping.dao.ShoppingCarDao;
import com.second.mall.modules.shopping.entity.ShoppingCar;
import com.second.mall.modules.shopping.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {

    @Autowired
    ShoppingCarDao shoppingCarDao;


    /**
     * 将商品添加到购物车
     * @param shoppingCar
     * @return
     */
    @Override
    public ShoppingCar userAddShoppingCar(ShoppingCar shoppingCar) {
        return null;
    }



}
