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

    //通过产品id和购物车id查询购物车是否存在该产品
    ShoppingCarItems findByProductIdAndShoppingCarId(int productId,int shoppingCarItemsId);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE shopping_car_items set number=number+1 WHERE product_id=:productId and shopping_car_id=:shoppingCarId", nativeQuery = true)
    int NumberOfProductsPlusOne(int productId,int shoppingCarId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "select *from shopping_car_items  WHERE shopping_car_id=:shoppingCarId and state= 1 ", nativeQuery = true)
    List<ShoppingCarItems> selectShoppingCarByState(Integer shoppingCarId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE shopping_car_items set state=:state WHERE shopping_car_items_id=:shoppingCarItemsId", nativeQuery = true)
    void updateState(Integer shoppingCarItemsId, int state);
}
