package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.dao.TAttributeMapper;
import com.mall.model.TAttribute;
import com.mall.service.TAttributeService;
import com.mall.util.DateUtil;
import com.mall.util.PageInfoUtil;
import com.mall.util.PageResult;
import com.mall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
@Service
public class TAttributeServiceImpl implements TAttributeService{
    @Autowired
    TAttributeMapper tAttributeMapper;


    public boolean checkBase(TAttribute attribute){
        boolean strFlag = StringUtils.isAnyEmpty(attribute.getName());
        return strFlag;
    }

    public Result save(TAttribute attribute){
        if (checkBase(attribute)){
            return new Result(1,"必填参数不能为空！");
        }

        attribute.setCreateTime(DateUtil.getCurrentDateTime());
        attribute.setUpdateTime(DateUtil.getCurrentDateTime());
        tAttributeMapper.insert(attribute);
        //插入参数和属性的映射
        return new Result(0,"success");
    }


    @Override
    public Result update(TAttribute attribute) {
        if (checkBase(attribute) || attribute.getId() == null){
            return new Result(1,"必填参数不能为空！");
        }
        TAttribute old = tAttributeMapper.selectByPrimaryKey(attribute.getId());
        if (old == null){
            return new Result(1,"id不存在！");
        }
        attribute.setUpdateTime(DateUtil.getCurrentDateTime());
        tAttributeMapper.updateByPrimaryKeySelective(attribute);

        return new Result(0,"success");
    }

    public Result getByPage(PageInfoUtil<TAttribute> info){
        PageHelper.startPage(info.getPage(),info.getPageSize(),"update_time desc");
        List<TAttribute> tAttribute = tAttributeMapper.selectByTAttribute(info.getInfo());
        PageInfo<TAttribute> attribute = new PageInfo<>(tAttribute);
        PageResult<TAttribute> pageResult = new PageResult<>(attribute.getTotal(),attribute.getList());
        return new Result(0,"success",pageResult);
    }

    public Result delete(Integer id){
        tAttributeMapper.deleteByPrimaryKey(id);
        return new Result(0,"success");
    }

    public Result getAll(){
        List<TAttribute> tAttribute = tAttributeMapper.selectByTAttribute(null);
        return new Result(tAttribute);
    }
}
