package com.demo.practical_training.common.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginResult{
    //用户id
    private String id;
    //操作代码
    private int code ;
    //提示信息
    private String message;
    //提示成功与否
    private String success;

    public LoginResult(String id, int code, String message ,String success) {
        this.id = id;
        this.code = code;
        this.message = message;
        this.success = success;
    }
}
