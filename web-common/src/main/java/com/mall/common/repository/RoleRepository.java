package com.mall.common.repository;

import com.mall.common.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface RoleRepository extends JpaRepository<Role, Long> ,JpaSpecificationExecutor<Role> {
}
