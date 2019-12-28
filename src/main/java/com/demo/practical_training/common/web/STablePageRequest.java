package com.demo.practical_training.common.web;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.apache.commons.lang3.StringUtils;

/*自定义的分页条件接收对象：
**提供根据前端的分页条件封装spring data jpa的分页对象Pageable。
**/
@Data
public class STablePageRequest {

    //分页条件
    private Integer 	pageNo 	= 1;
    private Integer 	pageSize	= 10;
    //排序条件
    private String  sortField	= null;
    private String  sortNews	= null;

    //接收前段的分页条件的 setter函数
   

    //3.
    public Pageable getPageable()
    {
        //前端分页 默认第一页为 1  ， Spring data jpa Pageable 默认第一页为0
        Pageable pageable = null;

        //如果排序条件不为null 或 ""
        if(StringUtils.isNotBlank(sortField) | StringUtils.isNotBlank(sortNews))
        {
            //new 一个默认 降序 排序对象Sort
            Sort pageSort = new Sort(Direction.DESC,sortField);
            //否则 new 升序  排序对象Sort
            if(!"descend".equals(sortNews)) {
                pageSort = new Sort(Direction.ASC,sortField);
            }

            //如果排序条件 不为null 或 ""  分页 + 排序
            pageable = PageRequest.of(pageNo-1, pageSize,pageSort);

        }else {
            //如果排序条件 为null 或 "" 则 只分页 不排序
            pageable = PageRequest.of(pageNo-1, pageSize);
        }

        return pageable;
    }
}
