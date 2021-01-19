package com.second.mall.modules.shopping.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName Product:商品
 * @Author icy
 * @Data 2020/12/29 20:27
 * @Version v1.0
 **/
@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private Integer pictureId;
    private Integer categoryId;
    private String pictureUrl;//图片地址
    private String productName;//商品名字
    private double productPrice;//商品价格
    private Integer productNum;//商品库存
    private Integer num;//购买数量
    private Integer discount;//折扣价
    private Integer shopId;
    private String describeInfo;//描述
    private int commentCount;  //评论数量
    private LocalDateTime createTime;//购买时间

    @Transient
    private List<Picture> bigPictures;
    @Transient
    private List<Picture> middlePictures;
    @Transient
    private List<Picture> smallPictures;
    @Transient
    private List<Picture> detailPictures;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getDescribeInfo() {
        return describeInfo;
    }

    public void setDescribeInfo(String describeInfo) {
        this.describeInfo = describeInfo;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<Picture> getBigPictures() {
        return bigPictures;
    }

    public void setBigPictures(List<Picture> bigPictures) {
        this.bigPictures = bigPictures;
    }

    public List<Picture> getMiddlePictures() {
        return middlePictures;
    }

    public void setMiddlePictures(List<Picture> middlePictures) {
        this.middlePictures = middlePictures;
    }

    public List<Picture> getSmallPictures() {
        return smallPictures;
    }

    public void setSmallPictures(List<Picture> smallPictures) {
        this.smallPictures = smallPictures;
    }

    public List<Picture> getDetailPictures() {
        return detailPictures;
    }

    public void setDetailPictures(List<Picture> detailPictures) {
        this.detailPictures = detailPictures;
    }
}
