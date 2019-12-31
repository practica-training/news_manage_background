package com.demo.practical_training.common.response;

import com.demo.practical_training.entity.UserVerified;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 分页统一响应
 */
@Data
@ToString
public class UserVerifiedResponseResult extends ResponseResult {

    List<UserVerified> list;
    //统一响应 数据集，操作是否成功，操作代码，提示信息
    public UserVerifiedResponseResult(ResultCode resultCode, List<UserVerified> list){
        super(resultCode);
        this.list = list;
    }

}
