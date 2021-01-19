package com.second.mall.modules.shopping.service;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Category;
import com.second.mall.modules.shopping.entity.Product;


import java.util.List;

public interface CategoryService {
    //添加种类
    ResultEntity<Category> insertCategory(Category category);

    //拿到种类id
    Category getCategoryById(int categoryId);

    //种类商品分页
    PageInfo<Category> getCategoriesBySearchVo(SearchBean searchVo);

    //
    PageInfo<Category> getCategoryBySearchVo(SearchBean searchVo);

    //根据种类对应的商品进行排序
    List<Category> getCategories();

    //
    ResultEntity<Category> updateCategory(Category category);

    //
    ResultEntity<Object> deleteCategoryById(int id);
}
