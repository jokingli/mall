package com.second.mall.modules.shopping.controller;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Address;
import com.second.mall.modules.shopping.entity.Comment;
import com.second.mall.modules.shopping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AddressController
 * @Author icy
 * @Data 2021/1/8 16:23
 * @Version v1.0
 **/
@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 新增地址
     * 127.0.0.1/api/address
     * {"address":"四川","linkman":"yyy","tel":"123456","userId":"1"}
     */
    @PostMapping(value = "/address" ,consumes = "application/json")
    private ResultEntity<Object> insertAddress(@RequestBody Address address){
        return addressService.insertAddress(address);
    }


    /**
     * 通过userId查询地址
     * 127.0.0.1/api/addresses/1
     */
    @GetMapping(value = "/addresses/{userId}")
    private ResultEntity<Object> selectAddressByUserId(@PathVariable int userId){
        return addressService.selectAddressByUserId(userId);
    }
}
