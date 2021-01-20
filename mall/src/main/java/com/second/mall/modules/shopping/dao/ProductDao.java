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




    //通过产品id集合查询
    @Select( "<script> "
            +"select * from product "
            +"<where> "
            +"product_id  in "
            +"<foreach collection='list' index='index' item='item' open='(' separator=',' close=')'> "
            +"#{item} "
            +"</foreach>"
            + "</where> "
            + "</script>")
    List<Product> selectProductByProductIdlist(List<Integer> productIdlist);

    //查询所有
    @Select("SELECT *FROM `product`")
    List<Product> selectProduct();

    @Select("select * from product where product_name like #{keyWord}")
    List<Product> selectProductByName(String keyWord);


    @Select("select * from product where product_id = #{productId}")
    Product getProductById(int productId);

    @Select("select * from product p left join category c on p.category_id = c.id "
            + "where category_id = #{categoryId} order by sale_count desc")
    List<Product> getProductsByCategoryId(int categoryId);

    @Select("<script>" +
            "select *, c.name as categoryName from product p left join category c on p.category_id = c.category_id"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (p.product_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by p.${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by p.product_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Product> getProductBySearchVo(ProductSearchVo productSearchVo);

    @Select("<script>" +
            "select * from product p left join category c on p.category_id = c.category_id"
            + "<where> p.category_id = #{categoryId} "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (p.product_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by p.${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by p.product_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Product> getProductsByProductSearchVo(ProductSearchVo productSearchVo);



    //站点搜索商品
    @Select("<script>" +
            "select *, c.name as categoryName from product p left join category c on p.category_id = c.id"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (p.name like '%${keyWord}%' or "
            + " p.sub_title like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by p.${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by p.id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Product> getProductsBySearchVo(SearchBean searchBean);





}
