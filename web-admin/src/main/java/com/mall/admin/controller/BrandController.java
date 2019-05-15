package com.mall.admin.controller;

import com.mall.common.entity.Brand;
import com.mall.admin.service.BrandService;
import com.mall.admin.service.CommonService;
import com.mall.admin.util.PageInfoUtil;
import com.mall.admin.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
@RestController
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private CommonService commonService;

    @PostMapping("/api/brand")
    public Result save(@Valid @RequestBody Brand brand, BindingResult bindingResult) {
        Result result = commonService.checkParam(bindingResult);
        if (result != null) {
            return result;
        }
        return brandService.save(brand);
    }

    @GetMapping("/api/brand")
    public Result queryByBrand(@RequestParam(value = "id",required = false) Long id,
                               @RequestParam(value = "name",required = false) String name,
                               @RequestParam(value = "updateTime",required = false) Long updateTime,
                               @RequestParam(value = "page",required = false) Integer page,
                               @RequestParam(value = "pageSize",required = false) Integer pageSize){
        Brand brand = Brand.builder().id(id).name(name).build();
        if (updateTime != null){
            brand.setUpdateTime(new Date(updateTime));
        }
        if (page != null && pageSize != null){
            PageInfoUtil<Brand> pageInfoUtil = new PageInfoUtil<>(page-1<0?0:page-1,pageSize,brand);
            return brandService.queryByBrandWithPage(pageInfoUtil);
        }
        return brandService.queryByBrand(brand);
    }

    @DeleteMapping("/api/brand/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return brandService.delete(id);
    }
}
