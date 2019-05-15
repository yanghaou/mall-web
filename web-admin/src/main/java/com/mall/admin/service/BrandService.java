package com.mall.admin.service;

import com.mall.common.entity.Brand;
import com.mall.common.util.PageInfoUtil;
import com.mall.common.util.Result;

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
