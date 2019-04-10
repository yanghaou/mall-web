package com.mall.dao;

import com.mall.model.TBrand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TBrandMapper {
    int insert(TBrand record);

    int insertSelective(TBrand record);
}