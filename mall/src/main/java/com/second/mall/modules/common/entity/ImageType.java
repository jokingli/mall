package com.second.mall.modules.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

/**
 * @ClassName ImageType
 * @Author icy
 * @Data 2020/12/31 14:54
 * @Version v1.0
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  ImageType {
    PRODUCT_BIG("product_big", 1200, 1300, 1024),
    PRODUCT_MIDDLE("product_middle", 220, 200, 1024),
    PRODUCT_SMALL("product_small", 56, 56, 1024),
    PRODUCT_DETAIL("product_detail", 800, 800, 1024),
    PROFILE_SMALL("profile_small", 53, 53, 1024),
    PROFILE_MIDDLE("profile_middle", 110, 110, 1024),
    PROFILE_BIG("profile_big", 800, 800, 1024);

    public String name;
    public Integer maxWidth;
    public Integer maxHeight;
    public int maxSize;

    private ImageType(String name, Integer maxWidth, Integer maxHeight, int maxSize) {
        this.name = name;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.maxSize = maxSize;
    }

    public static ImageType getImageTypeByName(String name) {
        return Arrays.stream(ImageType.values())
                .filter(item -> item.name.equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        System.out.println(ImageType.getImageTypeByName("product_big").maxHeight);
    }

}
