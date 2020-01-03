package com.demo.practical_training.common.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UploadResult {
    //url
    private String url;
    //操作代码
    private int code ;
    //提示信息
    private String message;
    //提示成功与否
    private Boolean success;

    public UploadResult(String url, int code, String message , Boolean success) {
        this.url = url;
        this.code = code;
        this.message = message;
        this.success = success;
    }
}
