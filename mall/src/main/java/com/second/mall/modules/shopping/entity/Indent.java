package com.second.mall.modules.shopping.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName Indent：订单
 * @Author icy
 * @Data 2020/12/31 14:42
 * @Version v1.0
 **/
@Entity
@Table(name = "indent")
@Data
public class Indent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int indentId;   //订单id
    private String indentCode;  //订单编号
    private int userId; //订单用户id
    private int addressId;  //收货地址id
    private String postPrice;   //邮费
    private double indentPrice; //订单总价格
    private String userMessage; //备注
    private LocalDateTime createTime;   //订单创建时间
    private LocalDateTime payTime;  //付款时间
    private LocalDateTime deliveryTime; //收货时间
    private LocalDateTime confirmTime;  //评论时间
    private int state;  //订单状态，0:未付款;1:未收货;2:已收货未评价;3:已评价;4:退货中;5:已退货;6:订单假删除

    @Transient
    private String userName;

    @Transient
    private String address; //收货地址
    @Transient
    private String linkman; //收货人
    @Transient
    private String tel; //收货人电话

    @Transient
    private List<IndentItem> indentItems;   //订单所属商品列
}
