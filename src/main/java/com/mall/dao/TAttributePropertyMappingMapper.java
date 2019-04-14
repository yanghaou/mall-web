package com.mall.dao;

import com.mall.model.TAttributePropertyMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TAttributePropertyMappingMapper {
    int deleteByMapping(TAttributePropertyMapping mapping);

    int insert(TAttributePropertyMapping record);

    int insertBatch(@Param("list") List<TAttributePropertyMapping> record);

    List<TAttributePropertyMapping> selectByMapping(TAttributePropertyMapping mapping);

    int updateByPrimaryKey(TAttributePropertyMapping record);

    List<Integer> selectPropertyIdByAttributeId(int id);
}