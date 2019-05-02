package com.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_brand")
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "品牌名称不能为空")
    private String name;
    @NotEmpty(message = "品牌首字母不能为空")
    private String firstChar;
    @NotNull(message = "品牌排序不能为空")
    private Byte orderNum;
    private String brandCompany;
    @NotEmpty(message = "品牌图标不能为空")
    private String brandLogo;
    private String brandBigIcon;
    @Column(name = "[show]")
    private Integer show;
    private Date createTime;
    private Date updateTime;
}