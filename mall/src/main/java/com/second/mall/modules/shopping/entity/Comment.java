package com.second.mall.modules.shopping.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName Comment
 * @Author icy
 * @Data 2020/12/31 14:34
 * @Version v1.0
 *
 * 评论
 **/

@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;//评论id
    private int productId;//评论商品id
    private int userId;//评论人id
    private int shopId;//商品店铺id
    private String comment;//评论内容
    private LocalDateTime createTime;//评论创建时间

}
