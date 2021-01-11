package com.second.mall.modules.shopping.service;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Comment;
import com.second.mall.modules.shopping.entity.Indent;

import java.util.List;

/**
 * @ClassName CommentService
 * @Author icy
 * @Data 2021/1/5 14:12
 * @Version v1.0
 **/
public interface CommentService {
    //评论新增
    ResultEntity<Comment> insertComment(Comment comment);

    //评论删除
    ResultEntity<Comment> deleteComment(int commentId);

    //所有评论
    List<Comment> selectComment();

    //修改评论
    ResultEntity<Object> updateComment(Comment comment);

    //分页
    PageInfo<Comment> getCommentBySearchBean(SearchBean searchBean);

    //通过id查询
    ResultEntity<Comment> selectCommentById(int commentId);
}
