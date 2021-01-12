package com.second.mall.modules.account.dao;

import com.second.mall.modules.account.entity.Resource;
import com.second.mall.modules.common.entity.SearchBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResourceDao {

    @Insert("insert resource(permission) value(#{permission})")
    @Options(useGeneratedKeys = true, keyProperty = "resourceId", keyColumn = "resource_id")
    void addResource(Resource resource);

    @Update("update resource set permission = #{permission} where resource_id = #{resourceId}")
    void updateResource(Resource resource);

    @Delete("delete from resource where resource_id = #{resourceId}")
    void deleteResource(int resourceId);

    @Select("select * from resource")
    List<Resource> getResources();

    @Select("<script>" +
            "select * from resource "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + "and permission like '%${keyWord}%' "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + "order by resource_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Resource> getResourcesBySearchVo(SearchBean searchBean);

    @Select("select * from resource resource left join role_resource roleResource on "
            + "resource.resource_id = roleResource.resource_id where roleResource.role_id = #{roleId}")
    List<Resource> getResourcesByRoleId(int roleId);

    @Select("select * from resource where resource_id=#{resourceId}")
    @Results(id="resourceResult", value={
            @Result(column="resource_id", property="resourceId"),
            @Result(column="resource_id",property="roles",
                    javaType=List.class,
                    many=@Many(select="com.thornbird.tmall.modules.account.dao."
                            + "RoleDao.getRolesByResourceId"))
    })
    Resource getResourceById(int resourceId);

    @Select("select * from resource where permission = #{permission}")
    Resource getResourceByPermission(String permission);
}
