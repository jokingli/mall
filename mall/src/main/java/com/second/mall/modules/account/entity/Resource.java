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


    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
