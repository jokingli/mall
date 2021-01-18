package com.second.mall.modules.shopping.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Shopping_car")
@Data
public class ShoppingCar {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer shoppingCarId;   //购物车id
        private Integer userId;    //用户id
        private Integer count;
        private double total;
        private LocalDateTime createTime;   //购物车创建时间


    }




