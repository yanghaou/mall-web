package com.mall.service.impl;

import com.mall.dao.TTypeMapper;
import com.mall.model.TType;
import com.mall.service.TypeService;
import com.mall.util.DateUtil;
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
public class TypeServiceImpl implements TypeService{
    @Autowired
    TTypeMapper tTypeMapper;

    public boolean checkBase(TType type){
        return StringUtils.isAnyEmpty(type.getName(),type.getUnit()) ||
                type.getOrder()==null;

    }

    public Result save(TType type){
        if (checkBase(type)){
            return new Result(1,"必填参数不能为空！");
        }

        type.setCreateTime(DateUtil.getCurrentDateTime());
        type.setUpdateTime(DateUtil.getCurrentDateTime());
        tTypeMapper.insert(type);
        return new Result(0,"success");
    }

    @Override
    public Result update(TType type) {
        if (checkBase(type) || type.getId() == null){
            return new Result(1,"必填参数不能为空！");
        }
        if (tTypeMapper.getById(type.getId()) == null){
            return new Result(1,"类型不存在！");
        }
        type.setUpdateTime(DateUtil.getCurrentDateTime());
        tTypeMapper.update(type);
        return new Result(0,"success");
    }

    public Result getAll(){
        List<TType> tTypes = tTypeMapper.getAll();
        return new Result(0,"success",tTypes);
    }

    public Result delete(Integer id){
        tTypeMapper.delete(id);
        return new Result(0,"success");
    }
}
