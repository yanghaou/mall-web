package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        return StringUtils.isAnyEmpty(property.getName(),property.getProperties()) || property.getOrderNum() == null;
    }

    public Result save(TProperty property){
        if (checkBase(property)){
            return new Result(1,"必填参数不能为空！");
        }

        property.setCreateTime(DateUtil.getCurrentDateTime());
        property.setUpdateTime(DateUtil.getCurrentDateTime());
        tPropertyMapper.insert(property);
        return new Result(0,"success");
    }

    @Override
    public Result update(TProperty property) {
        if (checkBase(property) || property.getId() == null){
            return new Result(1,"必填参数不能为空！");
        }
        if (tPropertyMapper.selectByPrimaryKey(property.getId()) == null){
            return new Result(1,"品牌不存在！");
        }
        property.setUpdateTime(DateUtil.getCurrentDateTime());
        tPropertyMapper.updateByPrimaryKey(property);
        return new Result(0,"success");
    }

    public Result getByPage(PageInfoUtil<TProperty> info){
        PageHelper.startPage(info.getPage(),info.getPageSize(),"update_time desc");
        List<TProperty> tProperty = tPropertyMapper.selectByTProperty(info.getInfo());
        PageInfo<TProperty> property = new PageInfo<>(tProperty);
        PageResult<TProperty> pageResult = new PageResult<>(property.getTotal(),property.getList());
        return new Result(0,"success",pageResult);
    }

    @Override
    public Result getByIdsIn(List<Integer> ids) {
        return new Result(tPropertyMapper.selectByPrimaryKeyIn(ids));
    }

    public Result getAll(){
        return new Result(tPropertyMapper.selectByTProperty(null));
    }

    public Result delete(Integer id){
        tPropertyMapper.deleteByPrimaryKey(id);
        return new Result(0,"success");
    }
}
