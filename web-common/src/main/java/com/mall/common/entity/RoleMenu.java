package com.mall.common.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_role_menu")
public class RoleMenu implements Serializable {
	
	private static final long serialVersionUID = -7573904024872252113L;

	private Long id;
    private Long roleId;
    private Long menuId;
}