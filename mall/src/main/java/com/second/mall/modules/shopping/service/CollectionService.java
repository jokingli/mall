package com.second.mall.modules.shopping.service;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Collection;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ShoppingCar;

import java.util.List;

public interface CollectionService {
    List<Product> selectCollection(int userId);

    ResultEntity<ShoppingCar> userAddCollection(int productId);

    ResultEntity<ShoppingCar> deleteConllectionByProductIdList(List<Integer> ids);

    ResultEntity<ShoppingCar> deletOneProduct(int productId);
}
