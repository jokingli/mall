package com.second.mall.modules.shopping.dao;

import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.ProductProductAtt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductProductAttDao {
    @Insert("insert into product_product_att (product_id, property_key, property_value) "
            + "values (#{productId}, #{propertyKey}, #{propertyValue})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertProductProperty(ProductProductAtt productProductAtt);

    @Select("<script>" +
            "select *, p.name as productName from product_product_att pp left join product p on pp.product_id = p.product_id "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (p.name like '%${keyWord}%' or "
            + " p.id like '%${keyWord}%' or "
            + " pp.property_key like '%${keyWord}%' or "
            + " pp.property_value like '%${keyWord}%' ) "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by pp.id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<ProductProductAtt> getProductPropertiesBySearchVo(SearchBean searchBean);

    @Select("select * from product_product_att where id = #{id}")
    ProductProductAtt getProductPropertyById(int id);

    @Select("select * from product_product_att where product_id = #{productId}")
    List<ProductProductAtt> getProductPropertiesByProductId(int productId);
}
