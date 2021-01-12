package com.second.mall.modules.shopping.dao;


import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Indent;
import com.second.mall.modules.shopping.entity.ShoppingCar;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShoppingCarDao extends JpaRepository<ShoppingCar, Integer> {

    /* 通过userId查询购物车*/
    ShoppingCar findByUserId(int userId);

    /*通过商品id删除购物车*/
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete  from shopping_car where product_id in :productIdList and user_id=:userId", nativeQuery = true)
    int deleteShoppingCarByProductIdList(List<Integer> productIdList,int userId);

    /*清空用户购物车*/
    int deleteAllByUserId(int userId);

    /*删除购物车某件商品*/
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM shopping_car where user_id=:userId and product_id=:poductId", nativeQuery = true)
    int deletOneProduct(@Param("userId") int userId, @Param("poductId") int poductId);


}
