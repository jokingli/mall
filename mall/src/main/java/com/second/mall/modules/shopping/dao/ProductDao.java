package com.second.mall.modules.shopping.dao;

import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.vo.ProductSearchVo;
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


    @Select("<script>"
            + "select *, c.category_name as categoryName from product p left join category c on p.category_id = c.category_id "
            + "where p.category_id = #{categoryId}"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (p.name like '%${keyWord}%' or "
            + " p.sub_title like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='order != \"\" and order != null'>"
            + " order by p.${order} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by p.category_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Product> getProductsByProductSearchVo(ProductSearchVo productSearchVo);

}
