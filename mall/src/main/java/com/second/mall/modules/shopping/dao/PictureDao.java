package com.second.mall.modules.shopping.dao;

import com.second.mall.modules.shopping.entity.Category;
import com.second.mall.modules.shopping.entity.Picture;
import com.second.mall.modules.shopping.entity.ProductProductAtt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PictureDao {
    //查询所有
    @Select("SELECT *FROM `picture`")
    List<Picture> selectPicture();


    @Select("select * from picture where product_id = #{productId}")
    List<Picture> getPictureByProductId(int productId);

    @Select("select * from picture where picture_id = #{pictureId}")
    Picture getPictureById(int pictureId);

    @Insert("insert into picture (picture_id,picture_ur, product_id,create_time, update_time) "
            + "values (#{pictureId}, #{pictureUr}, #{productId}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyColumn = "picture_id", keyProperty = "picture_id")
    void insertPicture(Picture picture);

}
