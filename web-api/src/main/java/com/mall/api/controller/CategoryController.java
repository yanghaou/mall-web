package com.mall.api.controller;

import com.mall.api.service.CategoryService;
import com.mall.common.entity.Category;
import com.mall.common.util.PageInfoUtil;
import com.mall.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @GetMapping("/api/category")
    public Result queryCategory(@RequestParam(value = "parentId", required = false) Long parentId) {
        Category category = Category.builder().parentId(parentId == null ? -1 : parentId).build();
        return categoryService.queryByCategory(category);
    }

}