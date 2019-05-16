package com.mall.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "父分类id不能为空，不存在时传-1")
    private Long parentId;
    @NotEmpty(message = "分类名称不能为空")
    private String name;
    @NotNull(message = "分类排序不能为空")
    private Byte orderNum;
    private String pic;
    private Date createTime;
    private Date updateTime;
}