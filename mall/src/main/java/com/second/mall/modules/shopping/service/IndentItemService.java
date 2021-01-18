package com.second.mall.modules.shopping.service;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Indent;
import com.second.mall.modules.shopping.entity.IndentItem;

import java.util.List;

/**
 * @ClassName IndentItemService
 * @Author icy
 * @Data 2021/1/13 14:10
 * @Version v1.0
 **/
public interface IndentItemService {
    //通过订单号查询id
    ResultEntity<IndentItem> selectIndentItemById(int IndentItemId);

    List<IndentItem> selectNoneCommentItems(String indentCode);
}
