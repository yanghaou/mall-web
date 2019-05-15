package com.mall.admin.service;

import com.mall.admin.entity.Category;
import com.mall.admin.util.PageInfoUtil;
import com.mall.admin.util.Result;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
public interface CategoryService {
    Result save(Category category);

    Result getAll();

    Result queryByCategory(Category category);

    Result queryByCategoryWithPage(PageInfoUtil<Category> vo);

    Result delete(Long id);
}
