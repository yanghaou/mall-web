package com.mall.repository;

import com.mall.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttributeRepository extends JpaRepository<Attribute, Long> ,JpaSpecificationExecutor<Attribute>{
}
