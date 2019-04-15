package com.mall.dao;

import com.mall.model.TGoodsSku;

public interface TGoodsSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TGoodsSku record);

    int insertSelective(TGoodsSku record);

    TGoodsSku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TGoodsSku record);

    int updateByPrimaryKey(TGoodsSku record);
}