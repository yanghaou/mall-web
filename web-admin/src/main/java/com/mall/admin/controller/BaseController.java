package com.mall.admin.controller;

import com.mall.common.util.Result;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


public class BaseController {
    public Result checkParam(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
            return new Result(-1, msg);
        }
        return null;
    }
}
