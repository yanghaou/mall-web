package com.mall.admin.controller;

import com.mall.admin.service.MenuService;
import com.mall.common.entity.Menu;
import com.mall.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class MenuController extends BaseController{
    @Autowired
    private MenuService menuService;

    @PostMapping("/api/menu")
    public Result save(@RequestBody @Valid Menu menu, BindingResult bindingResult){
        Result result = checkParam(bindingResult);
        if (result != null){
            return result;
        }
        return menuService.save(menu);
    }

    @GetMapping("/api/menu")
    public Result queryAll(@RequestParam(value = "type",required = false) Integer type,
                           @RequestParam(value = "name",required = false) String name){
        Menu menu = new Menu();
        menu.setType(type);
        menu.setName(name);
        return menuService.query(menu);
    }

    @GetMapping("/api/menu/tree")
    public Result getTree(){
        return menuService.getTree();
    }
}
