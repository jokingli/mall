package com.second.mall.modules.shopping.service.impl;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.dao.CollectionDao;
import com.second.mall.modules.shopping.dao.ProductDao;
import com.second.mall.modules.shopping.entity.Collection;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ShoppingCar;
import com.second.mall.modules.shopping.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionDao collectionDao;

    @Autowired
    ProductDao productDao;




    @Override
    public List<Product> selectCollection(int userId) {

        List<Collection> collectionList= collectionDao.findAllByUserId(userId);
        List<Integer> productIdList=new ArrayList<>();
        for (Collection collection: collectionList) {
            productIdList.add(collection.getProductId());

        }

        return productDao.selectProductByProductIdlist(productIdList);

    }

    @Override
    public ResultEntity<ShoppingCar> userAddCollection(int productId) {
        int userId =1;
        //查询是否已经存在该藏品
        Collection collection1=collectionDao.findByUserIdAndProductId(userId,productId);
            if(collection1==null) {
                Collection collection= new Collection();
                collection.setUserId(userId);
                collection.setProductId(productId);
                collection.setCreateTime(LocalDateTime.now());
                Collection collection2=collectionDao.save(collection);
                if (collection2!=null){
                    return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                            "添加成功");
                }
                return new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status,
                        "添加失败");
            }

        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "该产品已经在你的收藏中");


    }

    @Override
    public ResultEntity<ShoppingCar> deleteConllectionByProductIdList(List<Integer> ids) {
        int userId=1;
        int i=collectionDao.deleteConllectionByProductIdList(userId,ids);
        if(i>0){
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "删除成功");
        }else {
            return new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status,
                    "删除失败");
        }
    }

    @Override
    public ResultEntity<ShoppingCar> deletOneProduct(int productId) {
        int userId=1;
        int i=collectionDao.deletOneProduct(userId,productId);
        if(i>0){
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "删除成功");
        }else {
            return new ResultEntity<>(ResultEntity.ResultStatus.FAILED.status,
                    "删除失败");
        }
    }


}
