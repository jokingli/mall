package com.second.mall.modules.shopping.service;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Indent;

import java.util.List;

/**
 * @ClassName IndentService:订单
 * @Author icy
 * @Data 2021/1/4 14:38
 * @Version v1.0
 **/
public interface IndentService {
    //订单新增
    ResultEntity<Indent> insertIndent(Indent indent);
    //通过订单号查询id
    ResultEntity<Indent> selectIndentByCode(String indentCode);
    //真删除订单
    ResultEntity<Object> deleteIndexById(int indentId);

    //修改订单状态
    ResultEntity<Object> updateIndentState(Indent indent);

    //查询所有
    List<Indent> selectIndent();

    //分页
    PageInfo<Indent> getIndentBySearchBean(SearchBean searchBean);
}
