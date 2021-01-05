package com.second.mall.modules.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.dao.IndentDao;
import com.second.mall.modules.shopping.entity.Indent;
import com.second.mall.modules.shopping.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName IndentServiceImpl
 * @Author icy
 * @Data 2021/1/4 14:39
 * @Version v1.0
 **/
@Service
public class IndentServiceImpl implements IndentService {
    //注入订单dao层接口
    @Autowired
    private IndentDao indentDao;


    //订单新增
    @Override
    public ResultEntity<Indent> insertIndent(Indent indent) {
        //随机生成订单编号
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        Date date=new Date();
        indent.setIndentCode(""+hashCodeV+date.getTime());
        indent.setCreateTime(LocalDateTime.now());
        indent.setState(0);
        indentDao.insertIndex(indent);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "Indent insert success", indent);
    }

    //通过订单号查询id
    @Override
    public ResultEntity<Indent> selectIndentByCode(String indentCode) {
        System.out.println(indentCode);
        Indent indent = indentDao.selectIndexByCode(indentCode);
        System.out.println(indent);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "Indent select success", indent);
    }

    //真删除订单
    @Override
    public ResultEntity<Object> deleteIndexById(int indentId) {
        indentDao.deleteIndexById(indentId);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "Indent delete success");
    }

    //修改订单状态

    @Override
    public ResultEntity<Object> updateIndentState(Indent indent) {
        if (indent.getIndentCode()!=null){
            indentDao.updateIndent(indent);
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "Indent update state success");
        }
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "Not fond indent");
    }

    @Override
    public List<Indent> selectIndent() {
        return Optional.ofNullable(indentDao.selectIndent()).orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<Indent> getIndentBySearchBean(SearchBean searchBean) {
        searchBean.initSearchBean();
        PageHelper.startPage(searchBean.getCurrentPage(), searchBean.getPageSize());
        return new PageInfo<Indent>(Optional
                .ofNullable(indentDao.getIndentBySearchBean(searchBean))
                .orElse(Collections.emptyList()));
    }
}
