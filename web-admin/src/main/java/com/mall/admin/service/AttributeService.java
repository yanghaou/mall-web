package com.mall.admin.service;

import com.mall.common.entity.Attribute;
import com.mall.admin.util.PageInfoUtil;
import com.mall.admin.util.Result;

public interface AttributeService {
    Result save(Attribute attribute);

    Result queryByAttribute(Attribute attribute);

    Result queryByAttributeWithPage(PageInfoUtil<Attribute> vo);

    Result queryAttributeList(Long categoryId);

    Result delete(Long id);
}
