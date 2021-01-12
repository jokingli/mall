package com.second.mall.modules.shopping.dao;

import com.second.mall.modules.shopping.entity.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName AddressDao
 * @Author icy
 * @Data 2021/1/8 16:14
 * @Version v1.0
 **/
@Repository
@Mapper
public interface AddressDao {

    @Insert("INSERT INTO `address`" +
            "(`address`,linkman,tel,user_id,create_time,state)" +
            "VALUES" +
            "(#{address},#{linkman},#{tel},#{userId},#{createTime},#{state})")
    void insertAddress(Address address);

    //通过用户id查询地址
    @Select("SELECT *FROM `address` WHERE user_id = #{userId}")
    List<Address> selectAddressByUserId(int userId);
}
