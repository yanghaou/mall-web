package com.mall.model;

import com.alibaba.fastjson.JSON;


public class TGoodsWithBLOBs extends TGoods {
    private String picture;

    private String detail;

    private JSON skuStockList;

    public String getPicture() {
        return picture;
    }

    public JSON getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(JSON skuStockList) {
        this.skuStockList = skuStockList;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}