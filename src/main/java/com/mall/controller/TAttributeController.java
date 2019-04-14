package com.mall.controller;

import com.mall.model.TAttribute;
import com.mall.service.TAttributeService;
import com.mall.util.PageInfoUtil;
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
public class TAttributeController {
    @Autowired
    TAttributeService attributeService;

    @PostMapping("/api/attribute")
    public Result save(@RequestBody TAttribute attribute){
        return attributeService.save(attribute);
    }

    @GetMapping("/api/attribute")
    public Result getAll(){
        return attributeService.getAll();
    }

    @PutMapping("/api/attribute")
    public Result update(@RequestBody TAttribute attribute){
        return attributeService.update(attribute);
    }

    @PostMapping("/api/attribute/list")
    public Result getByPage(@Valid @RequestBody PageInfoUtil<TAttribute> attribute, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new Result(1,"参数异常",null);
        }
        return attributeService.getByPage(attribute);
    }

    @DeleteMapping("/api/attribute/{id}")
    public Result delete(@PathVariable("id") Integer id){
        return attributeService.delete(id);
    }
}
