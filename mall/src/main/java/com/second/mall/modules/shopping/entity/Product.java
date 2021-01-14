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
    private int productId;
    private Integer pictureId;
    private Integer categoryId;
    private String pictureUrl;
    private String productName;
    private double productPrice;
    private Integer productNum;//商品库存
    private Integer num;//购买数量
    private Integer discount;
    private Integer shopId;
    private String describeInfo;//描述
    private int commentCount;  //评论数量
    private LocalDateTime createTime;

    @Transient
    private List<Picture> bigPictures;
    @Transient
    private List<Picture> middlePictures;
    @Transient
    private List<Picture> smallPictures;
    @Transient
    private List<Picture> detailPictures;
}
