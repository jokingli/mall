package com.second.mall.modules.shopping.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName Collection
 * @Author icy
 * @Data 2020/12/31 14:56
 * @Version v1.0
 **/
@Entity
@Table(name = "collention")
@Data
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collectionId;
    private Integer userId;
    private Integer productId;
    private LocalDateTime createTime;
}
