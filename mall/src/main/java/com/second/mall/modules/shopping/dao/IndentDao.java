package com.second.mall.modules.shopping.dao;

import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Indent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName IndentDao：订单dao层
 * @Author icy
 * @Data 2021/1/4 14:27
 * @Version v1.0
 **/
@Repository
@Mapper
public interface IndentDao {
    //通过订单对象增加订单
    @Insert("INSERT INTO `indent` " +
            "(indent_code,user_id,address_id,post,post_price,indent_price,user_message,create_time,state) " +
            "VALUES " +
            "(#{indentCode},#{userId},#{addressId},#{post},#{postPrice},#{indentPrice},#{userMessage},#{createTime},#{state})")
    @Options(useGeneratedKeys = true,keyProperty = "indentId",keyColumn = "indent_id")
    void insertIndex(Indent indent);

    //通过订单号查询订单
    @Select("SELECT *FROM `indent` WHERE indent_code = #{indentCode}")
    Indent selectIndexByCode(String indentCode);

    //真删除订单
    @Delete("DELETE FROM `indent` WHERE indent_id = #{indentId}")
    void deleteIndexById(int indentId);

    //通过订单对象修改订单
    @Update("UPDATE `indent` SET state = #{state} WHERE indent_code = #{indentCode}")
    void updateIndent(Indent indent);

    //查询所有
    @Select("SELECT *FROM `indent`")
    List<Indent> selectIndent();

    @Select("<script>" +
            "SELECT indent.*, address.address, address.linkman, address.tel\n" +
            "FROM indent\n" +
            "LEFT JOIN address\n" +
            "ON indent.address_id = address.address_id "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (indent_code like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='order != \"\" and order != null'>"
            + " order by ${order} ${direction}"
            + "</when>"
            + "<otherwise>"
            + " order by indent_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Indent> getIndentBySearchBean(SearchBean searchBean);


    @Select("SELECT *FROM `indent` WHERE indent_id = #{indentId}")
    Indent selectIndexById(int indentId);
}
