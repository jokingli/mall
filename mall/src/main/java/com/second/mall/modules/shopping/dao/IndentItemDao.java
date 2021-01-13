package com.second.mall.modules.shopping.dao;

import com.second.mall.modules.shopping.entity.Indent;
import com.second.mall.modules.shopping.entity.IndentItem;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName IndentItemDao
 * @Author icy
 * @Data 2021/1/11 13:50
 * @Version v1.0
 **/
@Repository
@Mapper
public interface IndentItemDao {
    //通过订单列对象增加订单列
    @Insert("INSERT INTO `indent_item` " +
            "(product_id,product_num,indent_id,shop_id,create_time,postage,delivery,state) " +
            "VALUES " +
            "(#{productId},#{productNum},#{indentId},#{shopId},#{createTime},#{postage},#{delivery},#{state})")
    @Options(useGeneratedKeys = true,keyProperty = "indentItemId",keyColumn = "indent_item_id")
    void insertIndexItem(IndentItem indentItem);

    //通过订单id查询订单列
    @Select("SELECT indent_item.*, picture.picture_url, indent.indent_code" +
            ", shop.shop_name, product.product_name, product.product_price\n" +
            "FROM indent_item\n" +
            "LEFT JOIN product\n" +
            "ON indent_item.product_id = product.product_id\n" +
            "LEFT JOIN picture\n" +
            "ON product.picture_id = picture.picture_id\n" +
            "LEFT JOIN indent\n" +
            "ON indent_item.indent_id = indent.indent_id\n" +
            "LEFT JOIN shop\n" +
            "ON indent_item.shop_id = shop.shop_id\n" +
            "WHERE indent_item.indent_id =  #{indentId}")
    List<IndentItem> selectIndexItemByIndentId(int indentId);


    //通过订单列id查询订单列
    @Select("SELECT indent_item.*, picture.picture_url, indent.indent_code" +
            ", shop.shop_name, product.product_name, product.product_price\n" +
            "FROM indent_item\n" +
            "LEFT JOIN product\n" +
            "ON indent_item.product_id = product.product_id\n" +
            "LEFT JOIN picture\n" +
            "ON product.picture_id = picture.picture_id\n" +
            "LEFT JOIN indent\n" +
            "ON indent_item.indent_id = indent.indent_id\n" +
            "LEFT JOIN shop\n" +
            "ON indent_item.shop_id = shop.shop_id\n" +
            "WHERE indent_item_id =  #{indentItemId}")
    IndentItem selectIndexItemByIndentItemId(int indentItemId);

    @Update("update indent_item set state = #{state} where indent_item_id = #{indentItemId}")
    void updateIndexItemState(@Param("indentItemId")int indentItemId,@Param("state")int state);
}
