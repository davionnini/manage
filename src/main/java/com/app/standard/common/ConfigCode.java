package com.app.standard.common;

public enum  ConfigCode implements ErrorCode{
    SUCCESS(200,"操作成功"),
    FAILED(500,"服务异常"),
    OPERATE_FAILED(2000,"操作失败"),
    VALIDATE_FAILED(404,"参数校验失败"),
    TOKEN_EXIORED(401,"token过期"),
    FORBIDDEN(403,"操作禁止");

    private long code;
    private String message;

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private ConfigCode(long code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
