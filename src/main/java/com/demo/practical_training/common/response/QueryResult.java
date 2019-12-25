package com.demo.practical_training.common.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;

    public QueryResult() {
    }

    public QueryResult(List<T> list, long total) {
        this.list = list;
        this.total = total;
    }
}
