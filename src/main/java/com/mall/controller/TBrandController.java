package com.mall.controller;

import com.mall.model.TBrand;
import com.mall.service.TBrandService;
import com.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
@RestController
public class TBrandController {
    @Autowired
    TBrandService brandService;

    @PostMapping("/api/brand")
    public Result save(@RequestBody TBrand brand){
        return brandService.save(brand);
    }

    @PutMapping("/api/brand")
    public Result update(@RequestBody TBrand brand){
        return brandService.update(brand);
    }

    @GetMapping("/api/brand")
    public Result getAll(){
        return brandService.getAll();
    }

    @DeleteMapping("/api/brand/{id}")
    public Result delete(@PathVariable("id") Integer id){
        return brandService.delete(id);
    }
}
