package com.mall.common.util;

import java.io.Serializable;
import java.util.List;

/**
 * author :y.hao
 * time :2019/4/12
 * function:
 */
public class PageResult<T> implements Serializable {
    private long total;
    private List<T> list;

    public PageResult(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
