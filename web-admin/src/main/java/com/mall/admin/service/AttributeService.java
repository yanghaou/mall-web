package com.mall.admin.service;

import com.mall.common.entity.Attribute;
import com.mall.common.util.PageInfoUtil;
import com.mall.common.util.Result;

public interface AttributeService {
    Result save(Attribute attribute);

    Result queryByAttribute(Attribute attribute);

    Result queryByAttributeWithPage(PageInfoUtil<Attribute> vo);

    Result queryAttributeList(Long categoryId);

    Result delete(Long id);
}
