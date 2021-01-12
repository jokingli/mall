package com.second.mall.modules.shopping.service.impl;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.dao.AddressDao;
import com.second.mall.modules.shopping.entity.Address;
import com.second.mall.modules.shopping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName AddressServiceImpl
 * @Author icy
 * @Data 2021/1/8 16:19
 * @Version v1.0
 **/
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    public ResultEntity<Object> insertAddress(Address address) {
        address.setCreateTime(LocalDateTime.now());
        address.setState(1);
        addressDao.insertAddress(address);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "地址增加成功");
    }

    @Override
    public ResultEntity<Object> selectAddressByUserId(int userId) {
        return  new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "地址增加成功",addressDao.selectAddressByUserId(userId));
    }
}
