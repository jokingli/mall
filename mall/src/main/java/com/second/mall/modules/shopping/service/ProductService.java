package com.second.mall.modules.shopping.service;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.vo.ProductSearchVo;

import java.util.List;

public interface ProductService {
    List<Product> selectProduct();

    PageInfo<Product> getProductsBySearchVo(SearchBean searchVo);

    Product getProductByProductId(int productId);

    List<Product> getProductsByCategoryId(int categoryId);

    PageInfo<Product> getProductsByProductSearchVo(ProductSearchVo productSearchVo);


}
