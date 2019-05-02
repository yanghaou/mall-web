package com.mall.service;

import com.mall.util.Result;
import org.springframework.validation.BindingResult;

public interface CommonService {
    Result checkParam(BindingResult bindingResult);
}
