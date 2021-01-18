package com.second.mall.modules.shopping.dao;


import com.second.mall.modules.shopping.entity.Collection;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ShoppingCarItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CollectionDao extends JpaRepository<Collection, Integer> {


    List<Collection> findAllByUserId(int userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete  from collention where product_id in :productIdList and user_id=:userId", nativeQuery = true)
    int deleteConllectionByProductIdList(Integer userId, List<Integer> productIdList);




    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from collention where user_id=:userId and product_id =:productId ", nativeQuery = true)
    int deletOneProduct(int userId,int productId);
}
