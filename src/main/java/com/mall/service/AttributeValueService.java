package com.mall.service;

import com.mall.entity.AttributeValue;
import com.mall.util.Result;

public interface AttributeValueService {
    Result save(AttributeValue attribute);

    Result getAll();

    Result queryByAttributeValue(AttributeValue attributeValue);

    Result delete(Long id);
}
