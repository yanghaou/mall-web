package com.mall.common.repository;

import com.mall.common.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> ,JpaSpecificationExecutor<RoleMenu> {
}
