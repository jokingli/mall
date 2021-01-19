package com.second.mall.modules.shopping.service;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.vo.ProductSearchVo;

import java.util.List;

public interface ProductService {
    //查询所有，展示商品
    List<Product> selectProduct();

    //分页
    PageInfo<Product> getProductsBySearchVo(SearchBean searchBean);

    //根据id进入详情页
    Product getProductByProductId(int productId);

    //搜索
    PageInfo<Product> getProductsByProductSearchVo(ProductSearchVo productSearchVo);

    //根据商品名称进行搜索
    PageInfo<Product> selectProductByName(SearchBean searchBean);


}
