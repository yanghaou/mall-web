package com.mall.admin.repository;

import com.mall.admin.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface BrandRepository extends JpaRepository<Brand, Long> ,JpaSpecificationExecutor<Brand> {
}