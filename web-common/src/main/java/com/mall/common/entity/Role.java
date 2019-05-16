package com.mall.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "t_role")
public class Role implements Serializable {

	private static final long serialVersionUID = -1714476694755654924L;

	@Id
	private Long roleId;

	private String roleName;

	private String remark;

	private Date createTime;

	private Date modifyTime;
}