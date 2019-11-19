package com.demo.practical_training.common.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.apache.commons.lang3.StringUtils;

/*自定义的分页条件接收对象：
**提供根据前端的分页条件封装spring data jpa的分页对象Pageable。
**/
public class STablePageRequest {

    //分页条件
    private int 	pageNo 	= 1;
    private int 	pageSize	= 10;
    //排序条件
    private String  sortField	= "id";
    private String  sortNews	= "descend";

    //接收前段的分页条件的 setter函数
    public void setPageNo(int pageNo) {this.pageNo = pageNo;}
    public void setPageSize(int pageSize) {this.pageSize = pageSize;}
    public void setSortField(String sortField) {this.sortField = sortField;}
    public void setSortOrder(String sortOrder) {this.sortNews = sortOrder;}

    //3.
    public Pageable getPageable()
    {
        //前端分页 默认第一页为 1  ， Spring data jpa Pageable 默认第一页为0
        Pageable pageable = null;

        //如果排序条件不为null 或 ""
        if(StringUtils.isNotBlank(sortField)|| StringUtils.isNotBlank(sortNews))
        {
            //new 一个默认 降序 排序对象Sort
            Sort pageSort = new Sort(Direction.DESC,sortField);
            //否则 new 升序  排序对象Sort
            if(!sortNews.equals("descend")) {
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
