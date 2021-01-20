package com.second.mall.modules.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.dao.ProductProductAttDao;
import com.second.mall.modules.shopping.entity.ProductProductAtt;
import com.second.mall.modules.shopping.service.ProductProductAttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ProductProductAttServiceImpl implements ProductProductAttService {
@Autowired
private ProductProductAttDao productProductAttDao;
    @Override
    public ResultEntity<ProductProductAtt> insertProductProperty(ProductProductAtt productProductAtt) {
        productProductAttDao.insertProductProperty(productProductAtt);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status, "Insert success", productProductAtt);
    }

    @Override
    public ProductProductAtt getProductPropertyById(int id) {
        return productProductAttDao.getProductPropertyById(id);
    }

    @Override
    public PageInfo<ProductProductAtt> getProductPropertiesBySearchVo(SearchBean searchBean) {
        searchBean.initSearchBean();
        PageHelper.startPage(searchBean.getCurrentPage(), searchBean.getPageSize());
        return new PageInfo<ProductProductAtt>(
                Optional.ofNullable(productProductAttDao.getProductPropertiesBySearchVo(searchBean))
                        .orElse(Collections.emptyList()));
    }
    }

