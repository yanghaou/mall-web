package com.mall.common.repository;

import com.mall.common.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> ,JpaSpecificationExecutor<UserRole> {
}
