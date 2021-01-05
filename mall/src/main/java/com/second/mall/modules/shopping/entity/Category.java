package com.second.mall.modules.shopping.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName Category
 * @Author icy
 * @Data 2020/12/31 14:26
 * @Version v1.0
 **/
@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryName;
    private int parentId;
}
