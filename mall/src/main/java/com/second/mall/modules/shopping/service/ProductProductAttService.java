package com.second.mall.modules.shopping.service;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.ProductProductAtt;

public interface ProductProductAttService {

    ResultEntity<ProductProductAtt> insertProductProperty(ProductProductAtt productProductAtt);

    ProductProductAtt getProductPropertyById(int id);

    PageInfo<ProductProductAtt> getProductPropertiesBySearchVo(SearchBean searchBean);
}
