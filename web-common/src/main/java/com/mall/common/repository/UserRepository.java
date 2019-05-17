package com.mall.common.repository;

import com.mall.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserRepository extends JpaRepository<User, Long> ,JpaSpecificationExecutor<User> {
}
