package com.second.mall.modules.shopping.controller;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Indent;
import com.second.mall.modules.shopping.entity.IndentItem;
import com.second.mall.modules.shopping.service.IndentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndentItemController
 * @Author icy
 * @Data 2021/1/13 14:11
 * @Version v1.0
 **/
@RestController
@RequestMapping(value = "/api")
public class IndentItemController {
    @Autowired
    private IndentItemService indentItemService;

    /**
     * 127.0.0.1/api/indent/123 ---- get
     */
    @GetMapping(value = "/indentItem/{IndentItemId}")
    private ResultEntity<IndentItem> selectIndentByCode(@PathVariable int IndentItemId){
        return indentItemService.selectIndentItemById(IndentItemId);
    }
}
