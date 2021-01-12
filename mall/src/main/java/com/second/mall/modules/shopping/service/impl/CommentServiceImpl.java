package com.second.mall.modules.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.dao.CommentDao;
import com.second.mall.modules.shopping.dao.IndentDao;
import com.second.mall.modules.shopping.entity.Comment;
import com.second.mall.modules.shopping.entity.Indent;
import com.second.mall.modules.shopping.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName CommentServiceImpl
 * @Author icy
 * @Data 2021/1/5 14:13
 * @Version v1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IndentDao indentDao;

    @Override
    public ResultEntity<Comment> insertComment(Comment comment) {
        if (comment.getComments()!=null){
            comment.setCreateTime(LocalDateTime.now());
            commentDao.insertComment(comment);
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "评论增加成功");
        }
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "评论为空");
    }

    @Override
    public ResultEntity<Comment> deleteComment(int commentId) {
        commentDao.deleteComment(commentId);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "评论已删除");
    }

    @Override
    public List<Comment> selectComment() {
        return Optional.ofNullable(commentDao.selectComment()).orElse(Collections.emptyList());
    }

    @Override
    public ResultEntity<Object> updateComment(Comment comment) {
        Comment comment_db = commentDao.selectCommentById(comment.getCommentId());
        if (comment_db.getUserId()==comment.getUserId()){
            commentDao.updateComment(comment);
            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "评论修改成功");
        }
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "这不是你的评论");
    }

    //后端集合分页：显示评论id，订单编号，用户名，商铺名，评论内容，评论时间
    @Override
    public PageInfo<Comment> getCommentBySearchBean(SearchBean searchBean) {
        searchBean.initSearchBean();
        PageHelper.startPage(searchBean.getCurrentPage(), searchBean.getPageSize());
        List<Comment> commentBySearchBean = commentDao.getCommentBySearchBean(searchBean);
        for (Comment comment : commentBySearchBean) {
            if (comment.getIndentId()>0){
                Indent indent = indentDao.selectIndexById(comment.getIndentId());
                comment.setIndent(indent);
            }
        }
        return new PageInfo<Comment>(Optional
                .ofNullable(commentBySearchBean)
                .orElse(Collections.emptyList()));
    }

    @Override
    public ResultEntity<Comment> selectCommentById(int commentId) {
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "comment",commentDao.selectCommentById(commentId));
    }
}
