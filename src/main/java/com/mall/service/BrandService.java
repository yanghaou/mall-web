package com.mall.service;

import com.mall.entity.Brand;
import com.mall.util.PageInfoUtil;
import com.mall.util.Result;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
public interface BrandService {
    Result save(Brand brand);

    Result getAll();

    Result queryByBrand(Brand brand);

    Result queryByBrandWithPage(PageInfoUtil<Brand> vo);

    Result delete(Long id);
}