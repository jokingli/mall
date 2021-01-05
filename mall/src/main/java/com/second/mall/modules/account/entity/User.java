package com.second.mall.modules.account.entity;

import com.second.mall.modules.shopping.entity.Address;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName User
 * @Author icy
 * @Data 2020/12/29 14:22
 * @Version v1.0
 **/

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String password;
    private String tel;
    private String address;
    private String sex;
    private LocalDateTime createTime;
    private int state;
    private int del;

    @Transient
    private List<Role> roleList;

    @Transient
    private List<Address> addressList;

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", createTime=" + createTime +
                ", state=" + state +
                ", del=" + del +
                ", roleList=" + roleList +
                ", addressList=" + addressList +
                '}';
    }
}
