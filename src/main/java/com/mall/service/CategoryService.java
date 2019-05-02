package com.mall.service;

import com.mall.entity.Category;
import com.mall.util.Result;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
public interface CategoryService {
    Result save(Category category);

    Result getAll();

    Result queryByCategory(Category category);

    Result delete(Long id);
}
