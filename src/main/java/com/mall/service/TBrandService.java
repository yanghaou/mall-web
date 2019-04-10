package com.mall.service;

import com.mall.model.TBrand;
import com.mall.util.Result;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
public interface TBrandService {
    Result save(TBrand brand);
    Result update(TBrand brand);
    Result getAll();
    Result delete(Integer id);
}
