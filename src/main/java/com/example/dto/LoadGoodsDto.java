package com.example.dto;
import lombok.Data;

@Data
public class LoadGoodsDto {
    private String ship_companies;
    private String ship_names;
    private String work_begin_time;
    private String work_end_time;
    private String begin_time;
    private String end_time;
    private String work_port;
    private String tidang_number;
    private String container_number;
    private String container_size;
    private String begin_address;
    private String end_address;
}
