package com.mall.common.util;

public enum RspCode {
    SUCCESS(0,"success"),
    FAILED(1,"failed"),
    TOKEN_ERROR_OR_EXPIRED(2,"token错误或已过期"),
    NO_AUTH(3,"权限不足"),
    USERNAME_OR_PASSWORD_FAILED(4,"用户名或密码错误");

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
