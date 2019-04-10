package com.mall.dao;

import com.mall.model.TGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TGoods record);

    int insertSelective(TGoods record);

    TGoods selectByPrimaryKey(Long id);

    List<TGoods> selectByTGoods(TGoods goods);

    int updateByPrimaryKeySelective(TGoods record);

    int updateByPrimaryKey(TGoods record);
}