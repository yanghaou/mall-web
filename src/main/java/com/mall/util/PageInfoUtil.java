package com.mall.util;

import javax.validation.constraints.NotNull;

/**
 * author :y.hao
 * time :2019/4/10
 * function:
 */
public class PageInfoUtil<T> {
    @NotNull
    private Integer page;
    @NotNull
    private Integer pageSize;
    T Info;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getInfo() {
        return Info;
    }

    public void setInfo(T info) {
        Info = info;
    }
}
