package com.second.mall.modules.shopping.controller;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.service.ProductService;
import com.second.mall.modules.vo.ProductSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName LoginController
 * @Author icy
 * @Data 2020/12/28 16:51
 * @Version v1.0
 **/
@Controller
@RequestMapping(value = "/mall")
public class ProductController {
    @Autowired(required = false)
    private ProductService productService;


    @PostMapping(value = "/products", consumes = "application/json")
    @ResponseBody
    public PageInfo<Product> getProductsBySearchVo(@RequestBody SearchBean searchBean) {

        return productService.getProductsBySearchVo(searchBean);
    }


    @PostMapping(value = "/{categoryId}/products", consumes = "application/json")
    @ResponseBody
    public PageInfo<Product> getProductsByProductSearchVo(@RequestBody ProductSearchVo productSearchVo) {
        return productService.getProductsByProductSearchVo(productSearchVo);
    }


    @GetMapping(value ="/product/comment/{productId}")
    @ResponseBody
    public Product selectProductById(@PathVariable int productId) {
        return productService.getProductByProductId(productId);
    }

}
