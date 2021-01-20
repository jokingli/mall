package com.second.mall.modules.shopping.dao;

import com.second.mall.modules.shopping.entity.ShoppingCar;
import com.second.mall.modules.shopping.entity.ShoppingCarItems;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShoppingCarItemsDao extends JpaRepository<ShoppingCarItems, Integer> {

    //通过购物车id查询商品
    List<ShoppingCarItems> findAllByShoppingCarId(int shoppingCarId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete  from shopping_car_items where product_id in :productIdList and shopping_car_id=:shoppingCarId", nativeQuery = true)
    int deleteShoppingCarItemsByShoppingCarIdAndProductIdList(Integer shoppingCarId, List<Integer> productIdList);

    //清空商品列表
    @Transactional
    @Modifying(clearAutomatically = true)
    int deleteAllByShoppingCarId(int shoppingCarId);


    /*删除购物车某件商品*/
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM shopping_car_items where shopping_car_id=:shoppingCarId and product_id=:poductId", nativeQuery = true)
    int deletOneProduct(int  shoppingCarId, int poductId);

    /*修改商品数量*/
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE shopping_car_items SET number=:num where product_id=:poductId", nativeQuery = true)
    int changeNum(int num,int poductId);

    /*修改商品数量*/
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE shopping_car_items SET number=:number,state=:state where shopping_car_items_id=:shoppingCarItemsId", nativeQuery = true)
    void userUpdateShoppingCar(Integer shoppingCarItemsId, Integer number, int state);
}
