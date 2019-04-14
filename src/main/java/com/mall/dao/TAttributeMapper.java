package com.mall.dao;

import com.mall.model.TAttribute;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAttribute record);

    int insertSelective(TAttribute record);

    TAttribute selectByPrimaryKey(Integer id);

    List<TAttribute> selectByTAttribute(TAttribute attribute);

    int updateByPrimaryKeySelective(TAttribute record);

    int updateByPrimaryKey(TAttribute record);
}