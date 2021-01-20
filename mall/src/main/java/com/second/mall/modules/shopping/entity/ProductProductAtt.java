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
    private String propertyKey;
    private String propertyValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductAttId() {
        return productAttId;
    }

    public void setProductAttId(int productAttId) {
        this.productAttId = productAttId;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
