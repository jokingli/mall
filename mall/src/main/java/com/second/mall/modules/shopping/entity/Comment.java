package com.second.mall.modules.shopping.entity;

import com.second.mall.modules.account.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private int indentId;//订单id 0:表示无
    private int productId;//评论商品id  0：表示无
    private int userId;//评论人id
    private int shopId;//商品店铺id
    private int parentId;//评论父id 0:为最终路径
    private String comments;//评论内容
    private LocalDateTime createTime;//评论创建时间

    @Transient
    private Indent indent;  //所属订单编号
    @Transient
    private Product product;    //所属商品名
    @Transient
    private User user;  //所属用户名
    @Transient
    private Shop shop;  //所属商铺对象
    @Transient
    List<Picture> pictures; //评论所属图片

}
