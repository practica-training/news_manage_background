package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import lombok.Data;

@Data
public class LogResult  {
    private String operationalContent;
    private String time;

    public LogResult(String operationalContent, String time) {
        this.operationalContent = operationalContent;
        this.time = time;
    }
}
