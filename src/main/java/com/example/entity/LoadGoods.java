package com.example.entity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
public class LoadGoods {
    @TableField("SHIP_COMPANIES")
    private String ship_companies;

    @TableField("SHIP_NAMES")
    private String ship_names;

    @TableField("WORK_BEGIN_TIME")
    private String work_begin_time;

    @TableField("WORK_END_TIME")
    private String work_end_time;

    @TableField("BEGIN_TIME")
    private String begin_time;

    @TableField("END_TIME")
    private String end_time;

    @TableField("WORK_PORT")
    private String work_port;

    @TableField("TIDANG_NUMBER")
    private String tidang_number;

    @TableField("CONTAINER_NUMBER")
    private String container_number;

    @TableField("CONTAINER_SIZE")
    private String container_size;

    @TableField("BEGIN_ADDRESS")
    private String begin_address;

    @TableField("END_ADDRESS")
    private String end_address;

}
