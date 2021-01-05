package com.second.mall.modules.shopping.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName IndentItem：订单列
 * @Author icy
 * @Data 2020/12/31 14:51
 * @Version v1.0
 **/
@Entity
@Table(name = "indent_item")
@Data
public class IndentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int indentItemId;   //订单列id
    private int productName;    //商品名称
    private double productPrice; //商品单价
    private int productNum;     //商品数量
    private int indentId;       //订单id：所属订单
    private int shopId;     //店铺id
    private LocalDateTime createTime;   //创建时间

}
