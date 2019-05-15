package com.mall.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_attribute_value")
public class AttributeValue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "属性值绑定的属性id不能为空")
    private Long attributeId;

    @NotNull(message = "属性值不能为空")
    private String value;
    //排序
    @NotNull(message = "属性值排序不能为空")
    private Byte orderNum;
    private Date createTime;
    private Date updateTime;
}
