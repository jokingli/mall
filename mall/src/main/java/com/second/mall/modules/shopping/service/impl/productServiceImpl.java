package com.second.mall.modules.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.dao.ProductDao;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.service.ProductService;
import com.second.mall.modules.vo.ProductSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class productServiceImpl implements ProductService

{
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> selectProduct() {
        return Optional.ofNullable(productDao.selectProduct()).orElse(Collections.emptyList());
    }

   //分页
    @Override
    public PageInfo<Product> getProductsBySearchVo(SearchBean searchVo) {
        searchVo.initSearchBean();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        PageInfo<Product> pageInfo = new PageInfo<Product>(Optional
                .ofNullable(productDao.selectProduct())
                .orElse(Collections.emptyList()));
        return pageInfo;
    }

    //搜索

    @Override
    public PageInfo<Product> getProductsByProductSearchVo(ProductSearchVo productSearchVo) {
        productSearchVo.initSearchBean();
        PageHelper.startPage(productSearchVo.getCurrentPage(), productSearchVo.getPageSize());
        PageInfo<Product> pageInfo = new PageInfo<Product>(Optional
                .ofNullable(productDao.getProductsByProductSearchVo(productSearchVo))
                .orElse(Collections.emptyList()));
        pageInfo.setList(pageInfo.getList().stream().map(item -> initProduct(item)).collect(Collectors.toList()));
        return pageInfo;
    }



    @Override
    public Product getProductByProductId(int productId) {
        Product product = productDao.getProductById(productId);
        return initProduct(product);
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        return Optional.ofNullable(productDao.getProductsByCategoryId(categoryId)).orElse(Collections.emptyList());
    }

    private Product initProduct(Product product) {
        return product;
    }
}
