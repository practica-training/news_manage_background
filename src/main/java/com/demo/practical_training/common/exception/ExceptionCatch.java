package com.demo.practical_training.common.exception;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//异常捕获类:使用 @ControllerAdvice和@ExceptionHandler注解来捕获指定类型的异常
@ControllerAdvice
public class ExceptionCatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);
    //捕获CustomException类型的异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody//将json数据响应到响应体
    public ResponseResult getExceptionCatch(CustomException e){
        LOGGER.error("catch exception : {}\r\nexception: ",e.getMessage(), e);
        ResultCode resultCode = e.getResultCode();
        ResponseResult responseResult = new ResponseResult(resultCode);
        return responseResult;
    }
}
