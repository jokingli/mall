package com.second.mall.modules.shopping.dao;

import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName ProductDao
 * @Author icy
 * @Data 2021/1/7 10:45
 * @Version v1.0
 **/
@Repository
@Mapper
public interface ProductDao {
    //通过id查询商品
    Product selectProductById(int productId);

    List<Product> selectProductByProductIdlist(List<Integer> productIdlist);

    //查询所有
    @Select("SELECT *FROM `product`")
    List<Product> selectProduct();

    @Select("select * from product where product_id = #{productId}")
    Product getProductById(int productId);




    @Select("<script>" +
            "select * from product "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (product_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by product_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Product> getProductBySearchVo(SearchBean searchVo);
}
