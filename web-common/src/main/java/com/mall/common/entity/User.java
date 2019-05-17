package com.mall.common.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "t_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Long deptId;
    private String deptName;
    private String email;
    private String mobile;
    private String status;
    private Date crateTime;
    private Date modifyTime;
    private Date lastLoginTime;
    private String sex;
    private String theme;
    //头像
    private String avatar;
    private String description;
}