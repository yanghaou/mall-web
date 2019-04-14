package com.mall.service;

import com.mall.model.TAttribute;
import com.mall.util.PageInfoUtil;
import com.mall.util.Result;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
public interface TAttributeService {
    Result save(TAttribute attribute);
    Result update(TAttribute attribute);
    Result getByPage(PageInfoUtil<TAttribute> info);
    Result delete(Integer id);
    Result getAll();
}
