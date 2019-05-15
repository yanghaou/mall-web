package com.mall.admin.service;

import com.mall.common.entity.AttributeValue;
import com.mall.admin.util.PageInfoUtil;
import com.mall.admin.util.Result;

public interface AttributeValueService {
    Result save(AttributeValue attribute);

    Result getAll();

    Result queryByAttributeValue(AttributeValue attributeValue);

    Result queryByAttributeValueWithPage(PageInfoUtil<AttributeValue> vo);

    Result delete(Long id);
}
