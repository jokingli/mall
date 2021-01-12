package com.second.mall.modules.shopping.service;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> selectProduct();

    PageInfo<Product> getProductsBySearchVo(SearchBean searchVo);

    Product getProductByProductId(int productId);

    PageInfo<Product> getProductBySearchVo(SearchBean searchVo);
}