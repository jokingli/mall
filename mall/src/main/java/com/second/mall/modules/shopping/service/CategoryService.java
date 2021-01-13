package com.second.mall.modules.shopping.service;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Category;



import java.util.List;

public interface CategoryService {
    ResultEntity<Category> insertCategory(Category category);

    Category getCategoryById(int categoryId);

    PageInfo<Category> getCategoriesBySearchVo(SearchBean searchVo);

    List<Category> getCategories();

    ResultEntity<Category> updateCategory(Category category);

    ResultEntity<Object> deleteCategoryById(int id);
}
