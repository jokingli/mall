package com.second.mall.modules.shopping.dao;

import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Category;
import com.second.mall.modules.shopping.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryDao {
    @Insert("insert into category category_name values (#{categoryName})")
    @Options(useGeneratedKeys = true, keyColumn = "category_id", keyProperty = "category_id")
    void insertCategory(Category category);
    //查询所有
    @Select("SELECT *FROM `category`")
    List<Category> selectCategory();

    @Select("select * from category where category_name = #{categoryName}")
    Category getCategoryByName(String categoryName);

    @Select("select * from category where category_id = #{categoryId}")
    Category getCategoryById(int categoryId);

    @Select("<script>" +
            "select * from category "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (category_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by category_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Category> getCategoriesBySearchVo(SearchBean searchVo);

    @Select("select * from category order by category_id")
    List<Category> getCategories();

}
