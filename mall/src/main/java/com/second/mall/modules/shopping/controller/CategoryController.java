package com.second.mall.modules.shopping.controller;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Category;
import com.second.mall.modules.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 127.0.0.1/api/category ---- post
     */
    @PostMapping(value = "/category", consumes = "application/json")
    public ResultEntity<Category> insertCategory(@RequestBody Category category) {
        return categoryService.insertCategory(category);
    }

    /**
     * 127.0.0.1/api/category/1 ---- get
     */
    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    /**
     * 127.0.0.1/api/categories ---- post
     */
    @PostMapping(value = "/categories", consumes = "application/json")
    public PageInfo<Category> getCategoriesBySearchVo(@RequestBody SearchBean searchVo) {
        return categoryService.getCategoriesBySearchVo(searchVo);
    }

    /**
     * 127.0.0.1/api/categories ---- get
     */
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    /**
     * 127.0.0.1/api/category ---- put
     */
    @PutMapping(value = "/category", consumes = "application/json")
    public ResultEntity<Category> updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    /**
     * 127.0.0.1/api/category/1 ---- delete
     */
    @DeleteMapping("/category/{id}")
    public ResultEntity<Object> deleteCategoryById(@PathVariable int id) {
        return categoryService.deleteCategoryById(id);
    }
}