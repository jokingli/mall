package com.second.mall.modules.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.dao.ProductDao;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public PageInfo<Product> getProductBySearchVo(SearchBean searchVo) {
        searchVo.initSearchBean();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<Product>(
                Optional.ofNullable(productDao.getCategoriesBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }

    @Override
    public Product getProductByProductId(int productId) {
        Product product = productDao.getProductById(productId);
        return initProduct(product);
    }

    private Product initProduct(Product product) {
        return null;
    }
}
