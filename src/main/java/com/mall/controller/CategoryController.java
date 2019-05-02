package com.mall.controller;

import com.mall.entity.Category;
import com.mall.service.CategoryService;
import com.mall.service.CommonService;
import com.mall.util.Result;
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
    @Autowired
    private CommonService commonService;

    @PostMapping("/api/category")
    public Result save(@Valid @RequestBody Category category, BindingResult bindingResult) {
        Result result = commonService.checkParam(bindingResult);
        if (result != null) {
            return result;
        }
        return categoryService.save(category);
    }

    @GetMapping("/api/category")
    public Result queryCategory(@RequestParam(value = "id",required = false) Long id,
                                @RequestParam(value = "name",required = false) String name) {
        Category category = Category.builder().id(id).name(name).build();
        return categoryService.queryByCategory(category);
    }

    @DeleteMapping("/api/category/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return categoryService.delete(id);
    }
}
