package com.mall.common.repository;

import com.mall.common.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MenuRepository extends JpaRepository<Menu, Long> ,JpaSpecificationExecutor<Menu> {

    @Query(value = "SELECT * FROM t_menu WHERE id in (" +
            "SELECT DISTINCT(rm.menu_id) FROM t_role_menu rm " +
            "LEFT JOIN t_role r ON rm.role_id =r.id " +
            "LEFT JOIN t_user_role ur ON r.id = ur.role_id " +
            "LEFT JOIN t_user u ON ur.user_id =u.id " +
            "WHERE u.id =:userId )",nativeQuery = true)
    List<Menu> findByUserId(@Param("userId") Long userId);

    List<Menu> findByStatusAndValue(Integer status, String value);
}
