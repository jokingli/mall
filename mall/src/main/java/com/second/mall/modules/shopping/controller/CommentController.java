package com.second.mall.modules.shopping.controller;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Comment;
import com.second.mall.modules.shopping.entity.Indent;
import com.second.mall.modules.shopping.service.CommentService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CommentController
 * @Author icy
 * @Data 2021/1/5 14:35
 * @Version v1.0
 **/
@RestController
@RequestMapping(value = "/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 新增评论
     * 127.0.0.1/api/comment
     * {"productId":"1","userId":"1","shopId":"1","parentId":"0","comment":"aa"}
     */
    @PostMapping(value = "/comment")
    private ResultEntity<Comment> insertComment(@RequestBody Comment comment){
        return commentService.insertComment(comment);
    }

    /**
     * 127.0.0.1/api/comment/2 ---- delete
     */
    @DeleteMapping(value = "/comment/{commentId}")
    private ResultEntity<Comment> deleteComment(@PathVariable int commentId){
        return commentService.deleteComment(commentId);
    }

    /**
     * 127.0.0.1/api/comments --get
     */
    //查询所有
    @GetMapping(value = "/comments")
    private List<Comment> selectComment(){
        return commentService.selectComment();
    }

    /**
     * 127.0.0.1/api/comment ---- put
     * {"commentId":"3","productId":"1","userId":"1","shopId":"1","parentId":"0","comment":"aa"}
     */
    //通过修改订单状态
    @PutMapping(value = "/comment",consumes = "application/json")
    private ResultEntity<Object> updateIndent(@RequestBody Comment comment){
        return commentService.updateComment(comment);
    }
}
