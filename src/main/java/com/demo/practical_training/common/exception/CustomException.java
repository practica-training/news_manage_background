package com.demo.practical_training.common.exception;

import com.demo.practical_training.common.response.ResultCode;

/**
 * 自定义异常类
 */
public class CustomException extends RuntimeException {
    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return this.resultCode;
    }
}
