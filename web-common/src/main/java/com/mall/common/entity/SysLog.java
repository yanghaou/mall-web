package com.mall.common.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String operation;
	private Long time;
	private String method;
	private String params;
	private String ip;
	private Date createTime;
	private String location;
}