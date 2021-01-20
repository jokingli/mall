package com.second.mall.modules.shopping.entity;


import com.second.mall.modules.account.entity.Role;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "picture")
@Data
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pictureId;
    private String pictureUr;
    private Integer productId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
