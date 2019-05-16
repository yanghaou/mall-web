package com.mall.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "t_menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 7187628714679791771L;

	public static final String TYPE_MENU = "0";

	public static final String TYPE_BUTTON = "1";

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

	private Date modifyTime;

}