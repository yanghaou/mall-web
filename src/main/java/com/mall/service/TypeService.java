package com.mall.service;

import com.mall.model.TType;
import com.mall.util.Result;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
public interface TypeService {
    Result save(TType type);
    Result update(TType type);
    Result getAll();
    Result delete(Integer id);
}
