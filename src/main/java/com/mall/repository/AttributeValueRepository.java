package com.mall.repository;

import com.mall.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long>, JpaSpecificationExecutor<AttributeValue>{
}
