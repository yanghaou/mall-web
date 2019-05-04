package com.mall.service;

import com.mall.entity.Attribute;
import com.mall.util.PageInfoUtil;
import com.mall.util.Result;

public interface AttributeService {
    Result save(Attribute attribute);

    Result queryByAttribute(Attribute attribute);

    Result queryByAttributeWithPage(PageInfoUtil<Attribute> vo);

    Result delete(Long id);
}
