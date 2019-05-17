package com.mall.common.repository;

import com.mall.common.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface MenuRepository extends JpaRepository<Menu, Long> ,JpaSpecificationExecutor<Menu> {
}
