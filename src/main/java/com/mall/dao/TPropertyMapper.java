package com.mall.dao;

import com.mall.model.TProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TPropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProperty property);

    List<TProperty> selectByPrimaryKeyIn(@Param("list") List<Integer> id);

    TProperty selectByPrimaryKey(Integer id);
    
    List<TProperty> selectByTProperty(TProperty property);

    int updateByPrimaryKey(TProperty property);
}