package com.second.mall.modules.shopping.service.impl;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.dao.IndentItemDao;
import com.second.mall.modules.shopping.entity.IndentItem;
import com.second.mall.modules.shopping.service.IndentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName IndentItemServiceImpl
 * @Author icy
 * @Data 2021/1/13 14:11
 * @Version v1.0
 **/
@Service
public class IndentItemServiceImpl implements IndentItemService {
    @Autowired
    private IndentItemDao indentItemDao;

    @Override
    public ResultEntity<IndentItem> selectIndentItemById(int IndentItemId) {
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "状态修改成功",indentItemDao.selectIndexItemByIndentItemId(IndentItemId));
    }
}
