package com.mall.dao;


import com.mall.model.TType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TTypeMapper {
    int insert(TType record);
    int update(TType record);
    int insertSelective(TType record);
    List<TType> getAll();
    TType getById(Integer id);
    void delete(Integer id);
}