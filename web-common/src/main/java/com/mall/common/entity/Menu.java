package com.mall.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "t_menu")
public class Menu implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuId;

	private Long parentId;

	private String menuName;

	private String url;

	private String perms;

	private String icon;

	private String type;

	private Long orderNum;

	private Date createTime;

	private Date updateTime;

}