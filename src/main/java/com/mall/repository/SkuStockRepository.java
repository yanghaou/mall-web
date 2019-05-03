package com.mall.repository;

import com.mall.entity.Product;
import com.mall.entity.Sku;
import com.mall.entity.SkuStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SkuStockRepository extends JpaRepository<SkuStock,Long>{
    @Modifying
    @Transactional
    int deleteAllByProductId(Long productId);

    List<SkuStock> findBySkuIdIn(List<String> skuId);
}
