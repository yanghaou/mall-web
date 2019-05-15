package com.mall.admin.repository;

import com.mall.common.entity.Spu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SpuRepository extends JpaRepository<Spu,Long>{
    @Modifying
    @Transactional
    void deleteAllByProductId(Long productId);

    List<Spu> findByProductId(Long productId);

}
