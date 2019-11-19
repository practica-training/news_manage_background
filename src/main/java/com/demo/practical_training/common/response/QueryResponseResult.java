package com.demo.practical_training.common.response;

import lombok.Data;
import lombok.ToString;

/**
 * 分页统一响应
 */
@Data
@ToString
public class QueryResponseResult extends ResponseResult {

    QueryResult queryResult;
    //统一响应 数据集，操作是否成功，操作代码，提示信息
    public QueryResponseResult(ResultCode resultCode,QueryResult queryResult){
        super(resultCode);
        this.queryResult = queryResult;
    }

}
