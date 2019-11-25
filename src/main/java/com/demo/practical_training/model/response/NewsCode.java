package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResultCode;
import lombok.ToString;

/**
 * 自定义异常信息
 */
@ToString
public enum  NewsCode implements ResultCode {
    NEWS_NOT_ALLOW(false,500,"权限不足！");

    //操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private NewsCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
