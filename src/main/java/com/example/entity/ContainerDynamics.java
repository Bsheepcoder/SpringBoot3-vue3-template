package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ContainerDynamics {
    @TableField("STORAGE_PORT")
    private String storage_port;

    @TableField("CONTAINER_NUMBER")
    private String container_number;

    @TableField("CONTAINER_SIZE")
    private String container_size;

    @TableField("TIDANG_NUMBER")
    private String tidang_number;

    @TableField("STORAGE_ADDRESS")
    private String storage_address;

    @TableField("OPERATATION")
    private String operatation;

    @TableField("OPERATATION_TIME")
    private String operatation_time;
}
