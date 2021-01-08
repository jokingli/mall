package com.second.mall.modules.shopping.service;

import com.second.mall.modules.shopping.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> selectProductByProductIdlist(List<Integer> productIdlist);
}
