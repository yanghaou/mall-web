package com.mall.admin.repository;

import com.mall.common.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SkuRepository extends JpaRepository<Sku,Long>{
    @Modifying
    @Transactional
    int deleteAllByProductId(Long productId);

    List<Sku> findByProductId(Long productId);
}
