package com.example.utils;


import lombok.Data;

@Data
public class ResponseResult<T> {
    private static final ResponseResult<Void> OK = new ResponseResult<>();

    /**
     * 创建成功对象。
     *
     * @return 返回创建的ResponseResult实例对象
     */
    public static ResponseResult<Void> success() {
        return OK;
    }

}