package com.second.mall.modules.shopping.dao;


import com.second.mall.modules.shopping.entity.Indent;
import com.second.mall.modules.shopping.entity.ShoppingCar;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShoppingCarDao extends JpaRepository<ShoppingCar,Integer> {

    @Insert("INSERT INTO `indent` " +
            "(indent_code,user_id,address_id,indent_price,create_time,state) " +
            "VALUES " +
            "(#{indentCode},#{userId},#{addressId},#{indentPrice},#{createTime},#{state})")
    @Options(useGeneratedKeys = true,keyProperty = "indentId",keyColumn = "indent_id")
    void insertIndex(Indent indent);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "",nativeQuery = true)
    ShoppingCar userAddShoppingCar(ShoppingCar shoppingCar);


}
