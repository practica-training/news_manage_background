package com.demo.practical_training.common.exception;

import com.demo.practical_training.common.response.ResultCode;

//自定义异常抛出类
public class ExceptionCast {
    //使用静态方法抛出自定义异常
    private static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
