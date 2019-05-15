package com.mall.admin.repository;

import com.mall.common.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Long>,JpaSpecificationExecutor<Category> {
}
