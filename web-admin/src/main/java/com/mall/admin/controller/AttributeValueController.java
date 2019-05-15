package com.mall.admin.controller;

import com.mall.common.entity.AttributeValue;
import com.mall.admin.service.AttributeValueService;
import com.mall.admin.service.CommonService;
import com.mall.admin.util.PageInfoUtil;
import com.mall.admin.util.Result;
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
                                      @RequestParam(value = "attributeId") Long attributeId,
                                      @RequestParam(value = "value",required = false) String value,
                                      @RequestParam(value = "page",required = false) Integer page,
                                      @RequestParam(value = "pageSize",required = false) Integer pageSize) {
        AttributeValue attributeValue = AttributeValue.builder().id(id).attributeId(attributeId).value(value).build();
        if (page != null && pageSize != null){
            PageInfoUtil<AttributeValue> pageInfoUtil = new PageInfoUtil<>(page-1<0?0:page-1,pageSize,attributeValue);
            return attributeValueService.queryByAttributeValueWithPage(pageInfoUtil);
        }
        return attributeValueService.queryByAttributeValue(attributeValue);
    }

    @DeleteMapping("/api/attributeValue/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return attributeValueService.delete(id);
    }

}
