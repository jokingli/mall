package com.second.mall.modules.shopping.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName Product:商品
 * @Author icy
 * @Data 2020/12/29 20:27
 * @Version v1.0
 **/
@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;  //商品id
    private int categoryId; // 种类id
    private int pictureId;  //商品图片id
    private String productName; //商品名字
    private double productPrice;    //商品价格
    private int stockCount; // 进货数量
    private int productNum;//商品数量
    private int saleCount; // 销量
    private int discount;   //商品折扣
    private int shopId; //商店id
    private String describe_info;//描述
    private int reviewCount; // 评论数量
    private LocalDateTime createTime;   //商品创建时间

    @Transient
    private String shopName; // 卖家商铺
    @Transient
    private List<Comment> comments; //商品评价集合

}
