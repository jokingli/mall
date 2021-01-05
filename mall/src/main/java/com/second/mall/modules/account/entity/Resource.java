package com.second.mall.modules.account.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName Resource
 * @Author icy
 * @Data 2020/12/29 19:46
 * @Version v1.0
 * 种类
 **/
@Entity
@Table(name = "resource")
@Data
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resourceId;
    private String permission;
    @Transient
    private List<Role> roleList;
}
