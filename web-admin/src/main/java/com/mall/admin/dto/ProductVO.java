package com.mall.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO implements Serializable {
    private Long id;
    @NotEmpty(message = "商品名称不能为空")
    private String name;
    @NotNull(message = "商品分类不能为空")
    private Long categoryId;
    @NotNull(message = "商品状态不能为空")
    private Byte status;
    private Long brandId;
    private String subHead;
    private String detail;
    @NotNull(message = "商品排序不能为空")
    private Byte orderNum;
    @NotEmpty(message = "商品单位不能为空")
    private String unit;
    @NotEmpty(message = "商品图片不能为空")
    private String picture;
}
