package com.mall.admin.service.impl;

import com.mall.admin.service.CommonService;
import com.mall.common.util.Result;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

@Service
public class CommonServiceImpl implements CommonService {
    @Override
    public Result checkParam(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
            return new Result(-1, msg);
        }
        return null;
    }
}
