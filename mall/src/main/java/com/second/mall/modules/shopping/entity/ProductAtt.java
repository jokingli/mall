package com.second.mall.modules.shopping.entity;

import lombok.Data;
import sun.awt.SunHints;

import javax.persistence.*;

/**
 * @ClassName ProductAtt
 * @Author icy
 * @Data 2020/12/29 20:05
 * @Version v1.0
 **/
@Entity
@Table(name = "product_att")
@Data
public class ProductAtt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productAttId;
    private String attName;
    private String attDescribe;

}
