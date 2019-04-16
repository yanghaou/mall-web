package com.mall.service.impl;

import com.github.pagehelper.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.constant.ConstantParam;
import com.mall.dao.TPropertyMapper;
import com.mall.model.TProperty;
import com.mall.service.TPropertyService;
import com.mall.util.DateUtil;
import com.mall.util.PageInfoUtil;
import com.mall.util.PageResult;
import com.mall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
@Service
public class TPropertyServiceImpl implements TPropertyService{
    @Autowired
    TPropertyMapper tPropertyMapper;

    public boolean checkBase(TProperty property){
        return StringUtils.isAnyEmpty(property.getName())
                || property.getPropertyList() == null
                || property.getOrderNum() == null
                || property.getSelectModel() == null;
    }

    public Result save(TProperty property){
        if (checkBase(property)){
            return new Result(1,"必填参数不能为空！");
        }
        property.setProperties(String.join(ConstantParam.COMA,property.getPropertyList()));
        property.setCreateTime(DateUtil.getCurrentDateTime());
        property.setUpdateTime(DateUtil.getCurrentDateTime());
        tPropertyMapper.insert(property);
        return new Result(0,"success");
    }

    @Override
    public Result update(TProperty property) {
        if (checkBase(property)){
            return new Result(1,"必填参数不能为空！");
        }
        if (tPropertyMapper.selectByPrimaryKey(property.getId()) == null){
            return new Result(1,"品牌不存在！");
        }
        property.setProperties(String.join(ConstantParam.COMA,property.getPropertyList()));
        property.setUpdateTime(DateUtil.getCurrentDateTime());
        tPropertyMapper.updateByPrimaryKey(property);
        return new Result(0,"success");
    }

    public Result getByProperty(TProperty property){
        PageHelper.orderBy("update_time desc");
        List<TProperty> tProperty = tPropertyMapper.selectByTProperty(property);
        tProperty.forEach(t->{
            t.setPropertyList(Arrays.asList(t.getProperties().split(ConstantParam.COMA)));
            t.setProperties(null);
        });
        return new Result(0,"success",tProperty);
    }

    @Override
    public Result getByIdsIn(List<Integer> ids) {
        return new Result(tPropertyMapper.selectByPrimaryKeyIn(ids));
    }

    public Result getAll(){
        List<TProperty> tProperty = tPropertyMapper.selectByTProperty(null);
        tProperty.forEach(t->{
            t.setPropertyList(Arrays.asList(t.getProperties().split(ConstantParam.COMA)));
            t.setProperties(null);
        });
        return new Result(tProperty);
    }

    public Result delete(Integer id){
        tPropertyMapper.deleteByPrimaryKey(id);
        return new Result(0,"success");
    }
}
