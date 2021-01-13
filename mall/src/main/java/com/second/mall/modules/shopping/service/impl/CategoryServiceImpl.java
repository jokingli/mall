package com.second.mall.modules.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.dao.CategoryDao;
import com.second.mall.modules.shopping.entity.Category;
import com.second.mall.modules.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;


    @Override
    public ResultEntity<Category> insertCategory(Category category) {
        return null;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    @Override
    public PageInfo<Category> getCategoriesBySearchVo(SearchBean searchVo) {
        searchVo.initSearchBean();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<Category>(
                Optional.ofNullable(categoryDao.getCategoriesBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }

    @Override
    public List<Category> getCategories() {
        return Optional.ofNullable(categoryDao.getCategories()).orElse(Collections.emptyList());
    }

    @Override
    public ResultEntity<Category> updateCategory(Category category) {
        return null;
    }

    @Override
    public ResultEntity<Object> deleteCategoryById(int id) {
        return null;
    }
}
