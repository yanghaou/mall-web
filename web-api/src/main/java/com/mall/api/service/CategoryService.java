package com.mall.api.service;

import com.mall.common.entity.Category;
import com.mall.common.util.Result;

public interface CategoryService {
    Result queryByCategory(Category category);
}
