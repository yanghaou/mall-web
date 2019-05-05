package com.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_attribute_name")
public class Attribute implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "属性所属分类不能为空")
    private Long categoryId;

    @NotNull(message = "属性名称不能为空")
    private String name;
    //是否销售属性,0=基本属性，1=销售属性
    @NotNull(message = "属性类型不能为空")
    private Byte sale;
    //排序
    @NotNull(message = "属性排序不能为空")
    private Byte orderNum;
    @NotNull(message = "属性类型不能为空")
    private Byte multi;
    private Date createTime;
    private Date updateTime;
}
