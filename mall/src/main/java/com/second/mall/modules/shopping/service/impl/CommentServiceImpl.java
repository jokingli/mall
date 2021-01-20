package com.second.mall.modules.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.dao.CommentDao;
import com.second.mall.modules.shopping.dao.IndentDao;
import com.second.mall.modules.shopping.dao.IndentItemDao;
import com.second.mall.modules.shopping.dao.ProductDao;
import com.second.mall.modules.shopping.entity.Comment;
import com.second.mall.modules.shopping.entity.Indent;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    private IndentItemDao indentItemDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public ResultEntity<Comment> insertComment(Comment comment) {
        comment.setBrowseNum(0);
        comment.setThumbsNum(0);
        comment.setState(0);
        comment.setCreateTime(LocalDateTime.now());
        commentDao.insertComment(comment);
        Product productById = productDao.getProductById(comment.getProductId());
        int commentCount = productById.getCommentCount();
        productDao.commentCountAdd(commentCount+1,comment.getProductId());
        indentDao.updateStateForConfirm(comment.getIndentCode(),4,LocalDateTime.now());
        indentItemDao.updateIndexItemState(comment.getIndentItemId(),1);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "评论增加成功");

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
            if (comment.getComments()!=null){
                commentDao.updateComment(comment);
            }else if(comment.getState()!=0){
                commentDao.updateCommentState(comment);
            }

            return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                    "评论修改成功");
        }
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "这不是你的评论");
    }

    //后端集合分页：显示评论id，商品名称，店铺名称，评论用户，评论内容，评论时间,浏览次数,点赞数
    @Override
    public PageInfo<Comment> getCommentBySearchBean(SearchBean searchBean) {
        searchBean.initSearchBean();
        PageHelper.startPage(searchBean.getCurrentPage(), searchBean.getPageSize());
        List<Comment> commentBySearchBean = commentDao.getCommentBySearchBean(searchBean);

        return new PageInfo<Comment>(Optional
                .ofNullable(commentBySearchBean)
                .orElse(Collections.emptyList()));
    }

    @Override
    public ResultEntity<Comment> selectCommentById(int commentId) {
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status,
                "comment",commentDao.selectCommentById(commentId));
    }

    @Override
    public HashMap<String, Object> selectCommentByProductId(int productId) {
        HashMap<String,Object> hashMap = new HashMap<>();
        List<Comment> comments = commentDao.selectCommentByProductId(productId);
        List<Integer> parentIds = new ArrayList<>();
        for (Comment comment : comments) {
            boolean flag = true;
            for (Integer parentId : parentIds) {
                if (comment.getParentId() == parentId){
                    flag = false;
                }
            }
            if (flag){
                parentIds.add(comment.getParentId());
            }
        }
        hashMap.put("parentIds",parentIds);
        hashMap.put("comments",comments);
        return hashMap;
    }
}
