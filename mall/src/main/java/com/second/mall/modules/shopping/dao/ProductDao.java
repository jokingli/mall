package com.second.mall.modules.shopping.dao;

import com.second.mall.modules.shopping.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName ProductDao
 * @Author icy
 * @Data 2021/1/7 10:45
 * @Version v1.0
 **/
@Repository
@Mapper
public interface ProductDao {
    //通过id查询商品
    Product selectProductById(int productId);

    List<Product> selectProductByProductIdlist(List<Integer> productIdlist);
}
