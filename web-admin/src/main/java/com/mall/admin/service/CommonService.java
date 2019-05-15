package com.mall.admin.service;

import com.mall.common.util.Result;
import org.springframework.validation.BindingResult;

public interface CommonService {
    Result checkParam(BindingResult bindingResult);
}
