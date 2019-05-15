package com.mall.admin.service;

import com.mall.admin.util.Result;
import org.springframework.validation.BindingResult;

public interface CommonService {
    Result checkParam(BindingResult bindingResult);
}
