package com.second.mall.modules.shopping.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Shopping_car")
@Data
public class ShoppingCar {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int shoppingCarId;   //购物车id
        private int userId;    //用户id
        private int productId; //产品id
        private int productNum; //产品数量
        private LocalDateTime createTime;   //购物车创建时间


    }




