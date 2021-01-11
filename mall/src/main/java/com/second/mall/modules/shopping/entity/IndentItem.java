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
    private  int productId;     //商品Id
    private int productNum;     //购买数量
    private int indentId;       //订单id：所属订单
    private int shopId;     //店铺id
    private LocalDateTime createTime;   //创建时间
    private String delivery; //配送方式
    private double postage; //邮费

    @Transient
    private  String pictureUrl; //商品图片id

    @Transient
    private String productName;    //商品名称

    @Transient
    private double productPrice; //商品单价

    @Transient
    private String indentCode;  //订单编号

    @Transient
    private String shopName;    //店铺名字
}
