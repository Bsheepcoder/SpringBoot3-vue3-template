package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class LogisticsCompanies {
    @TableField("GOODS_COMPANIES")
    private String goods_companies;

    @TableField("CONTACTER_ID")
    private String contacter_id;

    @TableField("CONTACTER")
    private String contacter;

    @TableField("CONTACTER_PHONE_NUBER")
    private String contacter_phone_number;

    @TableField("CONTACTER_ADDRESS")
    private String contacter_address;
}
