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

    //访问登录页页面
    /*http://localhost:8080/mall/login*/
    @RequestMapping("/login")
    public String day02(Model model) {
        return "managerIndexSimple";
    }

    /*访问主页面  http://localhost:8080/mall/index*/
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList = productService.selectProduct();
        request.setAttribute("productList", productList);
        request.setAttribute("template", "mall/index");
        return "mallIndex";
    }

    //跳转到商城主页
    @RequestMapping("/home")
    public String homePage(ModelMap modelMap) {
        modelMap.put("template","fragment/selectProductByName");
        return "managerIndex";
    }

    //保存商品名，跳转到搜索页面
    @GetMapping(value = "/selectProductByName")
    public String selectProductByName(@RequestParam String keyWord, ModelMap modelMap) {
        modelMap.put("keyWord", keyWord);
        modelMap.put("template", "fragment/selectProductByName");
        return "mallIndex";
    }



    //分页
    @PostMapping(value = "/products", consumes = "application/json")
    @ResponseBody
    public PageInfo<Product> getProductsBySearchVo(@RequestBody SearchBean searchBean) {
        return productService.getProductsBySearchVo(searchBean);
    }

    //搜索
    @PostMapping(value = "/{categoryId}/products", consumes = "application/json")
    @ResponseBody
    public PageInfo<Product> getProductsByProductSearchVo(@RequestBody ProductSearchVo productSearchVo) {
        return productService.getProductsByProductSearchVo(productSearchVo);
    }


    @GetMapping("/searchResults")
    public String searchResultsPage(@RequestParam String keyWord, ModelMap modelMap) {
        modelMap.put("keyWord", keyWord);

        return "mallIndex";
    }

    @RequestMapping("/category/{categoryId}")
    public String categoryPage(@PathVariable int categoryId, ModelMap modelMap) {
        modelMap.addAttribute("categoryId", categoryId);
        modelMap.addAttribute("template", "mall/category");
        return "mallIndex";
    }

    @RequestMapping("/product/{productId}")
    public String getProductById(@PathVariable int productId, ModelMap modelMap) {
        modelMap.addAttribute("productId", productId);
        modelMap.addAttribute("template", "mall/product");
        return "mallIndex";
    }

    @GetMapping(value ="/product/{productId}", consumes = "application/json")
    @ResponseBody
    public Product selectProductById(@PathVariable int productId) {
        return productService.getProductByProductId(productId);
    }

}
