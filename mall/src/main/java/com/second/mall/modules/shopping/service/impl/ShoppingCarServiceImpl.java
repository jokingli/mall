package com.second.mall.modules.shopping.service.impl;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.dao.ShoppingCarDao;
import com.second.mall.modules.shopping.dao.ShoppingCarItemsDao;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ShoppingCar;
import com.second.mall.modules.shopping.entity.ShoppingCarItems;
import com.second.mall.modules.shopping.service.ProductService;
import com.second.mall.modules.shopping.service.ShoppingCarService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

    @Autowired
    ShoppingCarItemsDao shoppingCarItemsDao;

    /**
     * 将商品添加到购物车
     */
    @Override
    public ResultEntity<ShoppingCar> userAddShoppingCar(ShoppingCarItems shoppingCarItems) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        //通过session拿到用户id;
        int userId =user.getUserId();
        //查询用户是否存在购物车
        ShoppingCar shoppingCar = shoppingCarDao.findByUserId(userId);
        if (shoppingCar != null) {
            //判断产品是否在用户购物车中
            ShoppingCarItems shoppingCarItems1 = shoppingCarItemsDao.findByProductIdAndShoppingCarId(shoppingCarItems.getProductId(),shoppingCar.getShoppingCarId());
            if (shoppingCarItems1 != null) {
                int num = shoppingCarItems1.getNumber();
                //如果产品数量加一小于库存
                if (num + 1 <= shoppingCarItems1.getStock()) {
                    //购物车产品数加一
                    int i = shoppingCarItemsDao.NumberOfProductsPlusOne(shoppingCarItems1.getProductId(),shoppingCarItems1.getShoppingCarId());
                    if (i > 0) {
                        return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                                "添加购物车成功");
                    } else {
                        return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.FAILED.status,
                                "添加购物车失败");
                    }
                } else {

                    return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                            "添加购物车成功");
                }
            } else {

                shoppingCarItems.setShoppingCarId(shoppingCar.getShoppingCarId());
                ShoppingCarItems shoppingCarItems2 = shoppingCarItemsDao.save(shoppingCarItems);
                if (shoppingCarItems2 != null) {
                    return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                            "添加购物车成功");
                } else {
                    return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.FAILED.status,
                            "添加购物车失败");
                }
            }
        } else {
            //创建一个新的购物车
            ShoppingCar shoppingCar1 = new ShoppingCar();
            shoppingCar1.setUserId(userId);
            shoppingCar1.setCreateTime(LocalDateTime.now());
            ShoppingCar shoppingCar2 = shoppingCarDao.save(shoppingCar1);
            //将购物车id传入购物车商品列表中
            shoppingCarItems.setShoppingCarId(shoppingCar2.getShoppingCarId());

            ShoppingCarItems shoppingCarItems1 = shoppingCarItemsDao.save(shoppingCarItems);
            if (shoppingCarItems1 != null) {
                return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                        "添加购物车成功");
            } else {
                return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.FAILED.status,
                        "添加购物车失败");
            }
        }



    }

    /**
     * 通过用户id查询用户购物车中的商品
     */
    @Override
    public List<ShoppingCarItems> selectShopingCar(int userId) {
        ShoppingCar shoppingCar = shoppingCarDao.findByUserId(userId);
        return shoppingCarItemsDao.findAllByShoppingCarId(shoppingCar.getShoppingCarId());

    }

    /**
     * 通过用户id查询用户购物车中的商品
     */
    @Override
    public List<ShoppingCarItems> selectShoppingCarByState(int userId) {
        ShoppingCar shoppingCar = shoppingCarDao.findByUserId(userId);
        return shoppingCarItemsDao.selectShoppingCarByState(shoppingCar.getShoppingCarId());

    }
    /**
     * 批量删除购物车中商品
     */
    @Override
    public ResultEntity<ShoppingCar> deleteShoppingCarByProductIdList(List<Integer> productIdList, int userId) {
        //通过userId得到购物车id
        ShoppingCar shoppingCar = shoppingCarDao.findByUserId(userId);
        //通过购物车id和产品id集合删除
        int i = shoppingCarItemsDao.deleteShoppingCarItemsByShoppingCarIdAndProductIdList(shoppingCar.getShoppingCarId(), productIdList);
        if (i > 0) {
            return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                    "删除成功");
        } else {
            return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.FAILED.status,
                    "请选择一条数据");
        }

    }

    /**
     * 清空购物车
     */
    @Override
    public ResultEntity<ShoppingCar> clearShopingCarByUserId(int userId) {
        //通过userId得到购物车id
        ShoppingCar shoppingCar = shoppingCarDao.findByUserId(userId);
        //通过shoppingcCarId清空购物车商品列表
        int i = shoppingCarItemsDao.deleteAllByShoppingCarId(shoppingCar.getShoppingCarId());
        if (i > 0) {
            return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                    "已清空购物车");
        } else {
            return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.FAILED.status,
                    "购物车没有商品啦");
        }
    }

    @Override
    public ResultEntity<ShoppingCar> deletOneProduct(int userId, int poductId) {
        //通过userId得到购物车id
        ShoppingCar shoppingCar = shoppingCarDao.findByUserId(userId);
        //
        int i = shoppingCarItemsDao.deletOneProduct(userId, poductId);
        if (i > 0) {
            return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                    "删除成功");
        }
        return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.FAILED.status,
                "删除失败，请稍后重试");
    }

    @Override
    public ResultEntity<ShoppingCar> changeNum(int productId, int num) {
        int i = shoppingCarItemsDao.changeNum(num, productId);
        if (i > 0) {
            return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.SUCCESS.status,
                    "修改成功");
        }
        return new ResultEntity<ShoppingCar>(ResultEntity.ResultStatus.FAILED.status,
                "修改失败，请稍后重试");

    }

    @Override
    public ResultEntity<Object> userUpdateShoppingCar(List<ShoppingCarItems> shoppingCarItems) {
        for (ShoppingCarItems shoppingCarItem : shoppingCarItems) {
            shoppingCarItemsDao.userUpdateShoppingCar(shoppingCarItem.getShoppingCarItemsId(),shoppingCarItem.getNumber(),shoppingCarItem.getState());
        }
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "修改成功");
    }

    @Override
    public ResultEntity<Object> insertItemOne(ShoppingCarItems shoppingCarItems) {
        ShoppingCar byUserId = shoppingCarDao.findByUserId(shoppingCarItems.getUserId());
        shoppingCarItems.setShoppingCarId(byUserId.getShoppingCarId());
        shoppingCarItemsDao.save(shoppingCarItems);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "修改成功");
    }
}
