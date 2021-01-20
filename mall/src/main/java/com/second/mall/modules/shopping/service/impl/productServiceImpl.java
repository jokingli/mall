package com.second.mall.modules.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.dao.PictureDao;
import com.second.mall.modules.shopping.dao.ProductDao;
import com.second.mall.modules.shopping.dao.ProductProductAttDao;
import com.second.mall.modules.shopping.entity.Picture;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.entity.ProductProductAtt;
import com.second.mall.modules.shopping.service.ProductService;
import com.second.mall.modules.vo.ProductSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class productServiceImpl implements ProductService

{
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductProductAttDao productProductAttDao;
    @Autowired
    private PictureDao pictureDao;

    @Override
    public List<Product> selectProduct() {
        return Optional.ofNullable(productDao.selectProduct()).orElse(Collections.emptyList());
    }

   //分页
    @Override
    public PageInfo<Product> getProductsBySearchVo(SearchBean searchBean) {
        searchBean.initSearchBean();
        PageHelper.startPage(searchBean.getCurrentPage(), searchBean.getPageSize());
        PageInfo<Product> pageInfo = new PageInfo<Product>(Optional
                .ofNullable(productDao.selectProduct())
                .orElse(Collections.emptyList()));
        pageInfo.setList(pageInfo.getList().stream().map(item -> initProduct(item)).collect(Collectors.toList()));
        return pageInfo;
    }

    //搜索
    @Override
    public PageInfo<Product> getProductsByProductSearchVo(ProductSearchVo productSearchVo) {
        productSearchVo.initSearchBean();
        PageHelper.startPage(productSearchVo.getCurrentPage(), productSearchVo.getPageSize());
        PageInfo<Product> pageInfo = new PageInfo<Product>(Optional
                .ofNullable(productDao.getProductsByProductSearchVo(productSearchVo))
                .orElse(Collections.emptyList()));
        pageInfo.setList(pageInfo.getList().stream().map(item -> initProduct(item)).collect(Collectors.toList()));
        return pageInfo;
    }


    //根据商品名字查询商品
    @Override
    public PageInfo<Product> selectProductByName(SearchBean searchBean) {
        searchBean.initSearchBean();
        PageHelper.startPage(searchBean.getCurrentPage(), searchBean.getPageSize());
        PageInfo<Product> pageInfo = new PageInfo<Product>(Optional
                .ofNullable(productDao.selectProductByName(searchBean.getKeyWord()))
                .orElse(Collections.emptyList()));
        pageInfo.setList(pageInfo.getList().stream().map(item -> initProduct(item)).collect(Collectors.toList()));
        return pageInfo;
    }

    private Product initProduct(Product product) {
        List<Picture> productImages = Optional
                .ofNullable(pictureDao.getPictureByProductId(product.getProductId()))
                .orElse(Collections.emptyList());
        product.setPictureList(productImages);

        List<ProductProductAtt> productProperties = Optional
                .ofNullable(productProductAttDao.getProductPropertiesByProductId(product.getProductId()))
                .orElse(Collections.emptyList());
        product.setProductProperties(productProperties);
        return product;
    }

    @Override
    public Product getProductByProductId(int productId) {
        Product product = productDao.getProductById(productId);
        return initProduct(product);
    }

}
