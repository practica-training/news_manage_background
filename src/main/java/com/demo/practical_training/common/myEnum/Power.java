package com.demo.practical_training.common.myEnum;

import lombok.Data;

@Deprecated
public enum Power {
    NEWSAPPLY(1,"新闻管理"),USERAPPLY(2,"用户管理");
    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    Power(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
