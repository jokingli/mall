package com.second.mall.modules.shopping.service;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.entity.Category;
import com.second.mall.modules.shopping.entity.Picture;
import com.second.mall.modules.shopping.entity.Product;

import java.util.List;

public interface PictureService {
    //查询所有，展示图片
    List<Picture> selectPicture();

    //拿到种类id
    Picture getPictureById(int pictureId);

    ResultEntity<Picture> insertPicture(Picture picture);

    PageInfo<Picture> getPicturesBySearchBean(SearchBean searchBean);
}
