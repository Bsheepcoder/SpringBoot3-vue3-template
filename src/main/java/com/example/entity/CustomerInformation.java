package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class CustomerInformation {
    @TableField("OWNER_NAME")
    private String owner_name;

    @TableField("OWNER_ID")
    private String owner_id;

    @TableField("PHONE_NUMBER")
    private String phone_number;

    @TableField("OWNER_ADDRESS")
    private String owner_address;
}
