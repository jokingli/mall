package com.second.mall.modules.shopping.dao;


import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ShoppingCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {



    @Query(value = "SELECT * FROM product WHERE product_id in :productIdlist",nativeQuery = true)
    List<Product> selectProductByProductIdlist(List<Integer> productIdlist);


}
