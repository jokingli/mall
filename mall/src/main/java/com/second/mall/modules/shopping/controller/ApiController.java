package com.second.mall.modules.shopping.controller;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.account.dao.UserDao;
import com.second.mall.modules.account.service.UserService;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author yqs
 * @Comments 专门进行数据的查询与使用
 * @Date 2021/1/14 12:14
 **/
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ProductService productService;


    //根据商品名称进行搜索
    @PostMapping(value = "/products",consumes = "application/json")
    public PageInfo<Product> selectProductByName(@RequestBody SearchBean searchBean) {
        return productService.selectProductByName(searchBean);
    }

}
