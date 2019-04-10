package com.mall.service.impl;

import com.mall.dao.TTypeMapper;
import com.mall.model.TType;
import com.mall.service.TypeService;
import com.mall.util.DateUtil;
import com.mall.util.Result;
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

    public Result save(TType type){
        type.setCreateTime(DateUtil.getCurrentDateTime());
        type.setUpdateTime(DateUtil.getCurrentDateTime());
        tTypeMapper.insert(type);
        return new Result("200","success");
    }

    @Override
    public Result update(TType type) {
        type.setUpdateTime(DateUtil.getCurrentDateTime());
        tTypeMapper.update(type);
        return new Result("200","success");
    }

    public Result getAll(){
        List<TType> tTypes = tTypeMapper.getAll();
        return new Result("200","success",tTypes);
    }

    public Result delete(List<Integer> ids){
        tTypeMapper.delete(ids);
        return new Result("200","success");
    }
}
