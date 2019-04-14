package com.mall.service;

import com.mall.model.TProperty;
import com.mall.util.PageInfoUtil;
import com.mall.util.Result;

import java.util.List;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
public interface TPropertyService {
    Result save(TProperty property);
    Result update(TProperty property);
    Result getByIdsIn(List<Integer> ids);
    Result getByPage(PageInfoUtil<TProperty> info);
    Result delete(Integer id);
    Result getAll();
}
