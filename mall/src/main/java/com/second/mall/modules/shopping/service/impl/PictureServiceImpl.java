package com.second.mall.modules.shopping.service.impl;

import com.github.pagehelper.PageInfo;
import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.common.entity.SearchBean;
import com.second.mall.modules.shopping.dao.PictureDao;
import com.second.mall.modules.shopping.entity.Picture;
import com.second.mall.modules.shopping.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {
@Autowired
private PictureDao pictureDao;

    @Override
    public List<Picture> selectPicture() {
        return Optional.ofNullable(pictureDao.selectPicture()).orElse(Collections.emptyList());
    }


    @Override
    public Picture getPictureById(int pictureId) {
        return pictureDao.getPictureById(pictureId);
    }

    @Override
    public ResultEntity<Picture> insertPicture(Picture picture) {
        renameProductImage(picture);
       pictureDao.insertPicture(picture);
        return new ResultEntity<>(ResultEntity.ResultStatus.SUCCESS.status, "Insert image success.", picture);
    }

    private void renameProductImage(Picture picture) {

    }

    @Override
    public PageInfo<Picture> getPicturesBySearchBean(SearchBean searchBean) {
        return null;
    }


}
