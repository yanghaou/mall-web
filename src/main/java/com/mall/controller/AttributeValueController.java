package com.mall.controller;

import com.mall.entity.Attribute;
import com.mall.entity.AttributeValue;
import com.mall.service.AttributeValueService;
import com.mall.service.CommonService;
import com.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class AttributeValueController {
    @Autowired
    private AttributeValueService attributeValueService;
    @Autowired
    private CommonService commonService;

    @RequestMapping("/api/attributeValue")
    public Result save(@Valid @RequestBody AttributeValue attributeValue, BindingResult bindingResult) {
        Result result = commonService.checkParam(bindingResult);
        if (result != null) {
            return result;
        }
        return attributeValueService.save(attributeValue);
    }

    @GetMapping("/api/attributeValue")
    public Result queryAttributeValue(@RequestParam(value = "id",required = false) Long id,
                                 @RequestParam(value = "attributeId",required = true) Long attributeId,
                                 @RequestParam(value = "value",required = false) String value) {
        AttributeValue attributeValue = AttributeValue.builder().id(id).attributeId(attributeId).value(value).build();
        return attributeValueService.queryByAttributeValue(attributeValue);
    }

    @DeleteMapping("/api/attributeValue/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return attributeValueService.delete(id);
    }

}
