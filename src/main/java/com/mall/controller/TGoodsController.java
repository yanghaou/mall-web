package com.mall.controller;

import com.mall.model.TGoods;
import com.mall.service.TGoodsService;
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
public class TGoodsController {
    @Autowired
    TGoodsService goodsService;

    @PostMapping("/api/goods")
    public Result save(@RequestBody TGoods goods){
        return goodsService.save(goods);
    }

    @PutMapping("/api/goods")
    public Result update(@RequestBody TGoods goods){
        return goodsService.update(goods);
    }

    @PostMapping("/api/goods/list")
    public Result getByPage(@Valid @RequestBody PageInfoUtil<TGoods> goods, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new Result(1,"参数异常",null);
        }
        return goodsService.getByPage(goods);
    }

    @DeleteMapping("/api/goods/{id}")
    public Result delete(@PathVariable("id") Long id){
        return goodsService.delete(id);
    }
}
