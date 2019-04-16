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
    public Result getByProperty(
            @RequestParam(value = "attributeId",required = false) Integer attributeId,
            @RequestParam(value = "propertyType",required = false) Integer propertyType){
        TProperty property = new TProperty();
        property.setAttributeId(attributeId);
        property.setPropertyType(propertyType);
        return propertyService.getByProperty(property);
    }

    @PutMapping("/api/property")
    public Result update(@RequestBody TProperty property){
        return propertyService.update(property);
    }

    @DeleteMapping("/api/property/{id}")
    public Result delete(@PathVariable("id") Integer id){
        return propertyService.delete(id);
    }
}
