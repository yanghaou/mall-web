package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageSerializable;
import com.mall.dao.TGoodsMapper;
import com.mall.model.TGoods;
import com.mall.model.TGoodsWithBLOBs;
import com.mall.service.TGoodsService;
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
public class TGoodsServiceImpl implements TGoodsService{
    @Autowired
    TGoodsMapper tGoodsMapper;

    public boolean checkBase(TGoods goods){
        boolean strFlag = StringUtils.isAnyEmpty(goods.getName(),goods.getSubHead());
        boolean numFlag = goods.getOrderNum() == null
                || goods.getBrandId() == null
                || goods.getTypeId() == null ;
        return strFlag || numFlag;
    }

    public Result save(TGoodsWithBLOBs goods){
        if (checkBase(goods)){
            return new Result(1,"必填参数不能为空！");
        }
        //插入商品
        goods.setCreateTime(DateUtil.getCurrentDateTime());
        goods.setUpdateTime(DateUtil.getCurrentDateTime());
        tGoodsMapper.insert(goods);
        //插入sku

        return new Result(0,"success");
    }

    @Override
    public Result update(TGoodsWithBLOBs goods) {
        if (checkBase(goods) || goods.getId() == null){
            return new Result(1,"必填参数不能为空！");
        }
        if (tGoodsMapper.selectByPrimaryKey(goods.getId()) == null){
            return new Result(1,"品牌不存在！");
        }
        goods.setUpdateTime(DateUtil.getCurrentDateTime());
        tGoodsMapper.updateByPrimaryKey(goods);
        return new Result(0,"success");
    }

    public Result getByPage(PageInfoUtil<TGoods> info){
        PageHelper.startPage(info.getPage(),info.getPageSize(),"update_time desc");
        List<TGoods> tGoods = tGoodsMapper.selectByTGoods(info.getInfo());
        PageInfo<TGoods> goods = new PageInfo<>(tGoods);
        PageResult<TGoods> pageResult = new PageResult<>(goods.getTotal(),goods.getList());
        return new Result(0,"success",pageResult);
    }

    public Result delete(Long id){
        tGoodsMapper.deleteByPrimaryKey(id);
        return new Result(0,"success");
    }
}
