package com.mall.controller;

import com.mall.model.TProperty;
import com.mall.service.TPropertyService;
import com.mall.util.PageInfoUtil;
import com.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
@RestController
public class TPropertyController {
    @Autowired
    TPropertyService propertyService;

    @PostMapping("/api/property")
    public Result save(@RequestBody TProperty property){
        return propertyService.save(property);
    }

    @GetMapping("/api/property")
    public Result getAll(@RequestParam(value = "id",required = false) Integer[] id){
        if (id != null){
            return propertyService.getByIdsIn(Arrays.asList(id));
        }
        return propertyService.getAll();
    }

    @PutMapping("/api/property")
    public Result update(@RequestBody TProperty property){
        return propertyService.update(property);
    }

    @PostMapping("/api/property/list")
    public Result getByPage(@Valid @RequestBody PageInfoUtil<TProperty> property, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new Result(1,"参数异常",null);
        }
        return propertyService.getByPage(property);
    }

    @DeleteMapping("/api/property/{id}")
    public Result delete(@PathVariable("id") Integer id){
        return propertyService.delete(id);
    }
}
