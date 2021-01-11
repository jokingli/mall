package com.second.mall.modules.shopping.service;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Address;
import com.second.mall.modules.shopping.entity.Comment;

/**
 * @ClassName AddressService
 * @Author icy
 * @Data 2021/1/8 16:18
 * @Version v1.0
 **/
public interface AddressService {
    //新增地址
    ResultEntity<Object> insertAddress(Address address);

    //通过用户id查询
    ResultEntity<Object> selectAddressByUserId(int userId);
}
