package com.second.mall.modules.account.dao;

import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.common.entity.SearchBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("select * from user where user_name = #{userName}")
    User getUserByUserName(String userName);

    @Select("<script>" +
            "select * from user "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (user_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by user_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<User> getUsersBySearchVo(SearchBean searchBean);

    @Select("select * from user where user_id=#{userId}")
    @Results(id="userResult", value={
            @Result(column="user_id", property="userId"),
            @Result(column="user_id",property="roles",
                    javaType=List.class,
                    many=@Many(select="com.thornbird.tmall.modules.account.dao.RoleDao.getRolesByUserId"))
    })
    User getUserByUserId(int userId);

    @Update("update user set user_name = #{userName}, user_img = #{userImg} where user_id = #{userId}")
    void updateUser(User user);

    @Delete("delete from user where user_id = #{userId}")
    void deleteUser(int userId);

    @Select("select * from user where user_name = #{userName}")
    User queryByName(String userName);
}
