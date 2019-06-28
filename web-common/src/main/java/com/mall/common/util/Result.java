package com.mall.common.util;

import lombok.Data;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
@Data
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result(RspCode rspCode){
        this.code = rspCode.getCode();
        this.message = rspCode.getMsg();
    }

    public Result(RspCode rspCode, Object data){
        this.code = rspCode.getCode();
        this.message = rspCode.getMsg();
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Object data) {
        this(0, "success", data);
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(Object data){
        return new Result(RspCode.SUCCESS,data);
    }

    public static Result failed(String message){
        return new Result(RspCode.FAILED);
    }
}
