package com.mall.common.repository;

import com.mall.common.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MenuRepository extends JpaRepository<Menu, Long> ,JpaSpecificationExecutor<Menu> {
    @Query(nativeQuery = true,value = "SELECT " +
            " m.*  " +
            " FROM " +
            " t_menu m " +
            " LEFT JOIN t_role_menu rm ON m.id = rm.menu_id " +
            " LEFT JOIN t_role r ON rm.role_id = r.id " +
            " LEFT JOIN t_user_role ur ON r.id = ur.user_id " +
            " WHERE " +
            " ur.user_id = ?1")
    List<Menu> findByUserId(Long id);
}
