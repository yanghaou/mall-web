package com.mall.service.impl;

import com.mall.dao.TBrandMapper;
import com.mall.model.TBrand;
import com.mall.service.TBrandService;
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
public class TBrandServiceImpl implements TBrandService{
    @Autowired
    TBrandMapper tBrandMapper;

    public boolean checkBase(TBrand brand){
        boolean strFlag = StringUtils.isAnyEmpty(brand.getName(),brand.getBrandLogo());
        boolean numFlag = brand.getOrderNum() == null || brand.getShow() == null ;
        return strFlag || numFlag;

    }

    public Result save(TBrand brand){
        if (checkBase(brand)){
            return new Result(1,"必填参数不能为空！");
        }
        brand.setCreateTime(DateUtil.getCurrentDateTime());
        brand.setUpdateTime(DateUtil.getCurrentDateTime());
        tBrandMapper.insert(brand);
        return new Result(0,"success");
    }

    @Override
    public Result update(TBrand brand) {
        if (checkBase(brand) || brand.getId() == null){
            return new Result(1,"必填参数不能为空！");
        }
        if (tBrandMapper.getById(brand.getId()) == null){
            return new Result(1,"品牌不存在！");
        }
        brand.setUpdateTime(DateUtil.getCurrentDateTime());
        tBrandMapper.update(brand);
        return new Result(0,"success");
    }

    public Result getAll(){
        List<TBrand> tBrands = tBrandMapper.getAll();
        return new Result(0,"success",tBrands);
    }

    public Result delete(Integer id){
        tBrandMapper.delete(id);
        return new Result(0,"success");
    }
}
