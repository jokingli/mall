package com.second.mall.modules.shopping.controller;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.ProductProductAtt;
import com.second.mall.modules.shopping.service.ProductProductAttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductProductAttController {
    @Autowired
    private ProductProductAttService productProductAttService;
    @PostMapping(value = "/productProperty", consumes = "application/json")
    public ResultEntity<ProductProductAtt> insertProductProperty(@RequestBody  ProductProductAtt productProductAtt) {
        return productProductAttService.insertProductProperty(productProductAtt);
    }

    @GetMapping("/productProperty/{id}")
    public ProductProductAtt getProductPropertyById(@PathVariable int id) {
        return productProductAttService.getProductPropertyById(id);
    }


    @PostMapping(value = "/productProperties", consumes = "application/json")
    public PageInfo<ProductProductAtt> getProductPropertiesBySearchVo(@RequestBody SearchBean searchBean) {
        return productProductAttService.getProductPropertiesBySearchVo(searchBean);
    }
}
