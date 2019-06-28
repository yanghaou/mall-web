package com.mall.common.repository;

import com.mall.common.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> ,JpaSpecificationExecutor<RoleMenu> {
    @Modifying
    @Transactional
    int deleteByRoleId(Long roleId);
}
