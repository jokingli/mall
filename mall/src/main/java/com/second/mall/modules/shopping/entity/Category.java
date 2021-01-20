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
    private int categoryId;//种类id
    private String categoryName;//种类名
    private int parentId;//种类


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
