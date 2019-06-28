package com.mall.common.util;

public enum RspCode {
    SUCCESS(0,"success"),
    FAILED(1,"failed"),
    TOKEN_ERROR_OR_EXPIRED(2,"暂未登录或token已过期"),
    NO_AUTH(3,"权限不足"),
    PASSWORD_FAILED(4,"密码错误"),
    USER_EXIST(5,"用户名已存在"),
    USER_NOT_EXIST(6,"用户名不存在"),
    ;

    private int code;
    private String msg;


    RspCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
