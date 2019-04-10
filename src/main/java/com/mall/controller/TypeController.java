package com.mall.controller;

import com.mall.model.TType;
import com.mall.service.TypeService;
import com.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
@RestController("/api/type")
public class TypeController {
    @Autowired
    TypeService typeService;

    @PostMapping
    public Result save(@RequestBody TType type){
        return typeService.save(type);
    }

    @PutMapping
    public Result update(@RequestBody TType type){
        return typeService.update(type);
    }

    @GetMapping
    public Result getAll(){
        return typeService.getAll();
    }

    @DeleteMapping
    public Result delete(@RequestBody List<Integer> id){
        return typeService.delete(id);
    }
}
