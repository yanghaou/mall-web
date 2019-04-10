package com.mall.dao;

import com.mall.model.TBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TBrandMapper {
    int insert(TBrand record);
    int update(TBrand record);
    List<TBrand> getAll();
    TBrand getById(Integer id);
    void delete(Integer id);
}