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
            "(indent_item_id,product_id,user_id,shop_id,parent_id,`comments`,browse_num,thumbs_num,state,create_time)" +
            "VALUES" +
            "(#{indentItemId},#{productId},#{userId},#{shopId},#{parentId},#{comments},#{browseNum},#{thumbsNum},#{state},#{createTime})")
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
    @Update("UPDATE `comment` SET state = #{state} WHERE comment_id = #{commentId}")
    void updateCommentState(Comment comment);

    //查询评论通过id
    @Select("SELECT `comment`.*, product.product_name, shop.shop_name, `user`.user_name\n" +
            "FROM `comment`\n" +
            "INNER JOIN product\n" +
            "ON `comment`.product_id = product.product_id\n" +
            "INNER JOIN shop\n" +
            "ON `comment`.shop_id = shop.shop_id\n" +
            "INNER JOIN `user`\n" +
            "ON `comment`.user_id = `user`.user_id " +
            "WHERE comment_id = #{commentId}")
    Comment selectCommentById(int commentId);

    //通过userId查询
    @Select("SELECT *FROM `comment` WHERE user_id = #{userId}")
    Comment selectCommentByUserId(int userId);

    //通过productId查询
    @Select("SELECT `user`.user_name, `comment`.*\n" +
            "FROM `comment`\n" +
            "INNER JOIN `user`\n" +
            "ON `comment`.user_id = `user`.user_id\n " +
            "WHERE product_id = #{productId}")
    List<Comment> selectCommentByProductId(int productId);

    @Select("<script>"
            +"SELECT `comment`.*, product.product_name, shop.shop_name, `user`.user_name\n" +
            "FROM `comment`\n" +
            "INNER JOIN product\n" +
            "ON `comment`.product_id = product.product_id\n" +
            "INNER JOIN shop\n" +
            "ON `comment`.shop_id = shop.shop_id\n" +
            "INNER JOIN `user`\n" +
            "ON `comment`.user_id = `user`.user_id"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (`comments` like '%${keyWord}%' or product.product_name like '%${keyWord}%' " +
            "or shop.shop_name like '%${keyWord}%' or `user`.user_name like '%${keyWord}%') "
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
