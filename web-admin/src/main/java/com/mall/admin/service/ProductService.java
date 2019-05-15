package com.mall.admin.service;

import com.mall.admin.dto.ProductSkuVO;
import com.mall.admin.dto.ProductVO;
import com.mall.common.entity.Product;
import com.mall.admin.util.PageInfoUtil;
import com.mall.admin.util.Result;

public interface ProductService {
    //保存和更新
    Result saveProduct(ProductVO vo);

    Result deleteProduct(Long id);

    Result queryByProduct(PageInfoUtil<Product> vo);

    //保存和更新
    Result saveSku(ProductSkuVO vo);

    Result querySkuByProductId(Long productId);
}
