package com.mall.admin.service;

import com.mall.common.entity.Category;
import com.mall.common.util.PageInfoUtil;
import com.mall.common.util.Result;

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
