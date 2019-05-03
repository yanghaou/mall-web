package com.mall.service;

import com.mall.dto.ProductSkuVO;
import com.mall.dto.ProductVO;
import com.mall.entity.Product;
import com.mall.util.PageInfoUtil;
import com.mall.util.Result;

public interface ProductService {
    //保存和更新
    Result saveProduct(ProductVO vo);

    Result deleteProduct(Long id);

    Result queryByProduct(PageInfoUtil<Product> vo);

    //保存和更新
    Result saveSku(ProductSkuVO vo);

    Result querySkuByProductId(Long productId);
}
