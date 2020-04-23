package com.app.standard.common;

public class ReturnCode<T>{

    private String message;
    private long code;
    private T data;


    protected ReturnCode() {
    }

    protected ReturnCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    protected ReturnCode(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static  ReturnCode success() {
        return new ReturnCode(ConfigCode.SUCCESS.getCode(), ConfigCode.SUCCESS.getMessage());
    }

    public static <T> ReturnCode<T> success(T data) {
        return new ReturnCode<T>(ConfigCode.SUCCESS.getCode(), ConfigCode.SUCCESS.getMessage(), data);
    }

    public static <T> ReturnCode<T> success(T data, String message) {
        return new ReturnCode<T>(ConfigCode.SUCCESS.getCode(), message, data);
    }

    public static  ReturnCode fail(String message)
    {
        return new ReturnCode(ConfigCode.VALIDATE_FAILED.getCode(), message);
    }
}
