package com.second.mall.modules.shopping.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName Shop
 * @Author icy
 * @Data 2020/12/31 14:38
 * @Version v1.0
 **/
@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shopId;
    private String shopName;
    private int userId;//店铺拥有人
    private LocalDateTime createTime;//店铺创建时间
    private int state;//店铺状态
}
