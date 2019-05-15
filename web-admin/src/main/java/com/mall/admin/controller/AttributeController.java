package com.mall.admin.controller;

import com.mall.common.entity.Attribute;
import com.mall.admin.service.AttributeService;
import com.mall.admin.service.CommonService;
import com.mall.admin.util.PageInfoUtil;
import com.mall.admin.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class AttributeController {
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private CommonService commonService;

    @RequestMapping("/api/attribute")
    public Result save(@Valid @RequestBody Attribute attribute, BindingResult bindingResult) {
        Result result = commonService.checkParam(bindingResult);
        if (result != null) {
            return result;
        }
        return attributeService.save(attribute);
    }

    @GetMapping("/api/attribute")
    public Result queryAttribute(@RequestParam(value = "id",required = false) Long id,
                                @RequestParam(value = "categoryId",required = false) Long categoryId,
                                @RequestParam(value = "name",required = false) String name,
                                 @RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "pageSize",required = false) Integer pageSize) {

        Attribute attribute = Attribute.builder().id(id).categoryId(categoryId).name(name).build();
        if (page != null && pageSize != null){
            PageInfoUtil<Attribute> pageInfoUtil = new PageInfoUtil<>(page-1<0?0:page-1,pageSize,attribute);
            return attributeService.queryByAttributeWithPage(pageInfoUtil);
        }
        return attributeService.queryByAttribute(attribute);
    }
    @GetMapping("/api/attribute/{categoryId}")
    public Result queryAttribute(@PathVariable("categoryId") Long categoryId) {

        return attributeService.queryAttributeList(categoryId);
    }


    @DeleteMapping("/api/attribute/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return attributeService.delete(id);
    }

}
