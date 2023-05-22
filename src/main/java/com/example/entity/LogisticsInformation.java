package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class LogisticsInformation {
    @TableField("TIDANG_NUMBER")
    private String tidang_number;

    @TableField("OWNER_NAME")
    private String owner_name;

    @TableField("OWNER_ID")
    private String owner_id;

    @TableField("GOODS_COMPANIES")
    private String goods_companies;

    @TableField("CONTAINER_NUMBER")
    private String container_number;

    @TableField("GOODS_NAME")
    private String goods_name;

    @TableField("GOODS_WEIGHT")
    private String goods_weight;
}
