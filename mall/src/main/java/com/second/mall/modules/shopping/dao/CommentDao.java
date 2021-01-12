package com.second.mall.modules.shopping.dao;

import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Collection;
import com.second.mall.modules.shopping.entity.Comment;
import com.second.mall.modules.shopping.entity.Indent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName CommentDao
 * @Author icy
 * @Data 2021/1/5 13:28
 * @Version v1.0
 **/
@Repository
@Mapper
public interface CommentDao {
    //新增评论
    @Insert("INSERT INTO `comment`" +
            "(indent_id,product_id,user_id,shop_id,parent_id,`comments`,create_time)" +
            "VALUES" +
            "(#{indentId},#{productId},#{userId},#{shopId},#{parentId},#{comments},#{createTime})")
    void insertComment(Comment comment);

    //删除评论
    @Delete("DELETE FROM `comment` WHERE comment_id = #{commentId}")
    void deleteComment(int commentId);

    //查询所有
    @Select("SELECT *FROM `comment`")
    List<Comment> selectComment();

    //修改评论
    @Update("UPDATE `comment` SET comments = #{comments} WHERE comment_id = #{commentId}")
    void updateComment(Comment comment);

    //查询评论通过id
    @Select("SELECT *FROM `comment` WHERE comment_id = #{commentId}")
    Comment selectCommentById(int commentId);

    //通过userId查询
    @Select("SELECT *FROM `comment` WHERE user_id = #{userId}")
    Comment selectCommentByUserId(int userId);

    //通过userId查询
    @Select("SELECT *FROM `comment` WHERE product_id = #{productId}")
    Comment selectCommentByProductId(int productId);

    @Select("<script>"
            +"SELECT * FROM `comment`"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (`comment` like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='order != \"\" and order != null'>"
            + " order by ${order} ${direction}"
            + "</when>"
            + "<otherwise>"
            + " order by create_time desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Comment> getCommentBySearchBean(SearchBean searchBean);
}
