package com.mall.service;

import com.mall.model.TGoods;
import com.mall.model.TGoodsWithBLOBs;
import com.mall.util.PageInfoUtil;
import com.mall.util.Result;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
public interface TGoodsService {
    Result save(TGoodsWithBLOBs brand);
    Result update(TGoodsWithBLOBs brand);
    Result getByPage(PageInfoUtil<TGoods> info);
    Result delete(Long id);
}
