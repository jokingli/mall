package com.second.mall.modules.shopping.service;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.vo.ProductSearchVo;

import java.util.List;

public interface ProductService {
    List<Product> selectProduct();

    PageInfo<Product> getProductsBySearchVo(SearchBean searchBean);

    Product getProductByProductId(int productId);


    PageInfo<Product> getProductsByProductSearchVo(ProductSearchVo productSearchVo);

    //根据商品名称进行搜索
    PageInfo<Product> selectProductByName(SearchBean searchBean);


}
