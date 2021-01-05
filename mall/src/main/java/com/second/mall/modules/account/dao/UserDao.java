package com.second.mall.modules.account.dao;

import com.second.mall.modules.account.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserDao
 * @Author icy
 * @Data 2021/1/4 11:22
 * @Version v1.0
 **/

@Repository
@Mapper
public interface UserDao {
    @Insert("insert into user " +
            "(user_name,password,tel,address,sex,create_time,state,del) " +
            "values" +
            "(#{userName},#{password},#{tel},#{address},#{sex},#{createTime},#{state},#{del})")
    @Options(useGeneratedKeys = true,keyProperty = "userId",keyColumn = "user_id")
    void insertUser(User user);
}
