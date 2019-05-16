package com.mall.admin.controller;

import com.mall.common.entity.Category;
import com.mall.admin.service.CategoryService;
import com.mall.admin.service.CommonService;
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
    private CategoryService categoryService;
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
                                @RequestParam(value = "parentId",required = false) Long parentId,
                                @RequestParam(value = "name",required = false) String name,
                                @RequestParam(value = "page",required = false) Integer page,
                                @RequestParam(value = "pageSize",required = false) Integer pageSize) {
        Category category = Category.builder().id(id).parentId(parentId==null?-1:parentId).name(name).build();
        if (page != null && pageSize != null){
            PageInfoUtil<Category> pageInfoUtil = new PageInfoUtil<>(page-1<0?0:page-1,pageSize,category);
            return categoryService.queryByCategoryWithPage(pageInfoUtil);
        }
        return categoryService.queryByCategory(category);
    }

    @DeleteMapping("/api/category/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return categoryService.delete(id);
    }
}
