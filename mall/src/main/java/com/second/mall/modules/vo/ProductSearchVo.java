package com.second.mall.modules.vo;

import com.second.mall.modules.common.entity.SearchBean;

/**
 * Description: Product Search Vo
 * @author HymanHu
 * @date 2021-01-04 13:25:47
 */
public class ProductSearchVo extends SearchBean {
	
	private int categoryId;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
