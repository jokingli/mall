package com.second.mall.modules.shopping.controller;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Indent;
import com.second.mall.modules.shopping.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName IndentController
 * @Author icy
 * @Data 2021/1/4 15:07
 * @Version v1.0
 **/
@RestController
@RequestMapping(value = "/api")
public class IndentController {
    //注入service层接口
    @Autowired
    private IndentService indentService;

    /**
     * 新增订单insertIndex --post
     * 127.0.0.1/api/indent
     * {"userId":"1","addressId":"1","indentPrice":"12"}
     */
    @PostMapping(value = "/indent", consumes = "application/json")
    private ResultEntity<Indent> insertIndent(@RequestBody Indent indent){
        return indentService.insertIndent(indent);
    }

    /**
     * 127.0.0.1/api/indent/123 ---- get
     */
    @GetMapping(value = "/indent/{indentCode}")
    private ResultEntity<Indent> selectIndentByCode(@PathVariable String indentCode){
        return indentService.selectIndentByCode(indentCode);
    }

    /**
     * 127.0.0.1/api/indent/123 ---- delete
     */
    //通过id真删除
    @DeleteMapping(value = "/indent/{indentId}")
    private ResultEntity<Object> deleteIndentById(@PathVariable int indentId){
        return indentService.deleteIndexById(indentId);
    }

    /**
     * 127.0.0.1/api/indent ---- put
     * {"indentId":"5","indentCode":"16743719731609749048723","state":"2"}
     */
    //通过修改订单状态
    @PutMapping(value = "/indent",consumes = "application/json")
    private ResultEntity<Object> updateIndent(@RequestBody Indent indent){
        return indentService.updateIndentState(indent);
    }

    /**
     * 127.0.0.1/api/indents --get
     */
    //查询所有
    @GetMapping(value = "/indents")
    private List<Indent> selectIndent(){
        return indentService.selectIndent();
    }

    /**
     * 127.0.0.1/api/indents ---- post
     * {"currentPage":1, "pageSize":5, "order":"indent_code", "direction":"desc", "keyWord":""}
     */
    @PostMapping(value = "/indents", consumes = "application/json")
    public PageInfo<Indent> getCategoriesBySearchBean(
            @RequestBody SearchBean searchBean) {
        return indentService.getIndentBySearchBean(searchBean);
    }

}
