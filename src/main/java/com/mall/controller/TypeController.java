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
@RestController
public class TypeController {
    @Autowired
    TypeService typeService;

    @PostMapping("/api/type")
    public Result save(@RequestBody TType type){
        return typeService.save(type);
    }

    @PutMapping("/api/type")
    public Result update(@RequestBody TType type){
        return typeService.update(type);
    }

    @GetMapping("/api/type")
    public Result getAll(){
        return typeService.getAll();
    }

    @DeleteMapping("/api/type/{id}")
    public Result delete(@PathVariable("id") Integer id){
        return typeService.delete(id);
    }
}
