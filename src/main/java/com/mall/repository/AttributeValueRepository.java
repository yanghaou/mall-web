package com.mall.repository;

import com.mall.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long>, JpaSpecificationExecutor<AttributeValue>{
    List<AttributeValue> findByAttributeIdInOrderByOrderNumDesc(List<Long> attributeId);
}
