package com.second.mall.modules.shopping.controller;

import com.second.mall.modules.common.entity.ResultEntity;
import com.second.mall.modules.shopping.entity.Category;
import com.second.mall.modules.shopping.entity.Picture;
import com.second.mall.modules.shopping.entity.Product;
import com.second.mall.modules.shopping.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PictureController {
    @Autowired
    private PictureService pictureService;
    /**
     * 拿到图片数据http://localhost:8080/api/picture
     *
     */
    @PostMapping( "/picture")
    public  List<Picture> selectPicture() {
        return pictureService.selectPicture();
    }


    @PostMapping(value ="/pictures/{pictureId}", consumes = "application/json")
    public Picture selectPicture(Model model, @PathVariable int pictureId) {
        List<Picture> pictureList = pictureService.selectPicture();
        model.addAttribute("pictureList",pictureList);
       return pictureService.getPictureById(pictureId);
    }

}
