package com.second.mall.modules.shopping.entity;


import lombok.Data;

import javax.persistence.*;
//购物车商品列表
@Entity
@Table(name = "Shopping_car_items")
@Data
public class ShoppingCarItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shoppingCarItemsId;   //购物车id
    private Integer productId;
    private Integer shoppingCarId;
    private String productName;
    private double productPrice;
    private double discount;//折扣
    private String image;
    private Integer stock;//产品库存
    private Integer number;//购买数量
    private double total;//总价
    private int state;

    @Transient
    private int userId;

}
