package com.mall.service;

import com.mall.entity.Attribute;
import com.mall.util.Result;

public interface AttributeService {
    Result save(Attribute attribute);

    Result queryByAttribute(Attribute attribute);

    Result delete(Long id);
}
