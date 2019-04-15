package com.mall.dao;

import com.mall.model.TSku;

public interface TSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TSku record);

    int insertSelective(TSku record);

    TSku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSku record);

    int updateByPrimaryKeyWithBLOBs(TSku record);
}