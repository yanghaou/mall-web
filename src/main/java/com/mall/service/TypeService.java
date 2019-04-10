package com.mall.service;

import com.mall.model.TType;
import com.mall.util.Result;

import java.util.List;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
public interface TypeService {
    Result save(TType type);
    Result update(TType type);
    Result getAll();
    Result delete(List<Integer> id);
}
