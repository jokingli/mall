package com.second.mall.modules.shopping.service.impl;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.dao.ShoppingCarDao;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ShoppingCar;
import com.second.mall.modules.shopping.service.ProductService;
import com.second.mall.modules.shopping.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {

    @Autowired
    ShoppingCarDao shoppingCarDao;

    @Autowired
    ProductService productService;


    /**
     * 将商品添加到购物车
     */
    @Override
    public ResultEntity<ShoppingCar> userAddShoppingCar(ShoppingCar shoppingCar) {

        shoppingCar.setCreateTime(LocalDateTime.now());
        ShoppingCar shoppingCar1  = shoppingCarDao.save(shoppingCar);
        if (shoppingCar1 != null) {
           return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                    "添加购物车成功");
        }
            return  new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                    "添加购物车失败");
    }

    /**
     *通过用户id查询用户购物车中的商品
     */
    @Override
    public List<Product> selectShopingCar(int userId) {
        List<ShoppingCar> shoppingCarList=shoppingCarDao.findAllByUserId(userId);

        List<Integer> productIdlist = new ArrayList<>();
        for (ShoppingCar shoppingCar : shoppingCarList) {
            productIdlist.add(shoppingCar.getProductId());
        }
        List<Product> productList = productService.selectProductByProductIdlist(productIdlist);
        return productList;
    }
    /**
     *批量删除购物车中商品
     */
    @Override
    public ResultEntity<ShoppingCar> deleteShoppingCarByProductIdList(List<Integer> productIdList,int userId) {
        int i=shoppingCarDao.deleteShoppingCarByProductIdList(productIdList,userId);
        if(i>0){
            return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                    "删除成功");

        }else{
            return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.FAILED.status,
                    "请选择一条数据");
        }

    }

    /**
     *清空购物车
     */
    @Override
    public ResultEntity<ShoppingCar> clearShopingCarByUserId(int userId) {
        int i=shoppingCarDao.deleteAllByUserId(userId);
        if (i > 0) {
            return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                    "已清空购物车");
        }else {
            return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.FAILED.status,
                    "购物车没有商品啦");
        }
    }

    @Override
    public ResultEntity<ShoppingCar> deletOneProduct(int userId,int poductId) {
        int i=shoppingCarDao.deletOneProduct(userId,poductId);
        if (i>0){
            return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                    "删除成功");
        }
        return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.FAILED.status,
                "删除失败，请稍后重试");
    }


}
