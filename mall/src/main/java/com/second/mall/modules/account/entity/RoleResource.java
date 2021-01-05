package com.second.mall.modules.account.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName RoleResource
 * @Author icy
 * @Data 2020/12/29 19:48
 * @Version v1.0
 **/
@Entity
@Table(name = "role_resource")
@Data
public class RoleResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleResourceId;
    private int roleId;
    private int resourceId;
}
