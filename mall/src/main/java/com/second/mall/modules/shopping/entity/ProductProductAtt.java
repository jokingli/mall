package com.second.mall.modules.shopping.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName ProductProductAtt
 * @Author icy
 * @Data 2020/12/31 14:31
 * @Version v1.0
 **/
@Entity
@Table(name = "product_product_att")
@Data
public class ProductProductAtt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private int productAttId;
}
