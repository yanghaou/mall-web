package com.mall.common.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_role_menu")
public class RoleMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Long roleId;
    private Long menuId;
}