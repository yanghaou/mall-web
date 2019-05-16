package com.mall.common.util;

public enum RspCode {
    SUCCESS(0,"success"),
    FAILED(1,"failed");

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
