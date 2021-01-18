package com.second.mall.modules.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.dao.CategoryDao;
import com.second.mall.modules.shopping.entity.Category;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;



    public void renameCategoryImage(Category category) {

    }

    @Override
    @Transactional
    public ResultEntity<Category> insertCategory(Category category) {
        Category categoryTemp = categoryDao.getCategoryByName(category.getCategoryName());
        if (categoryTemp != null) {
            return new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status, "Category name is repeat.");
        }

        renameCategoryImage(category);
        categoryDao.insertCategory(category);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status, "Insert category success.", category);
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

    //分页
    @Override
    public PageInfo<Category> getCategoryBySearchVo(SearchBean searchVo) {
        searchVo.initSearchBean();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        PageInfo<Category> pageInfo = new PageInfo<Category>(Optional
                .ofNullable(categoryDao.selectCategory())
                .orElse(Collections.emptyList()));
        return pageInfo;
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
