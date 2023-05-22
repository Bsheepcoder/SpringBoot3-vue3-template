package com.example.exception;


public class BusinessException extends Exception {
    private static final long serialVersionUID = -3804995326646218863L;
    private String errCode;
    private String errMsg;

    public BusinessException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }
}