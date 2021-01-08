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
        private Integer shoppingCarId;   //购物车id
        private Integer userId;    //用户id
        private Integer productId; //产品id
        private LocalDateTime createTime;   //购物车创建时间


    }




