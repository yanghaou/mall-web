package com.mall.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkuStockVO implements Serializable {
    @NotEmpty(message = "skuId不能为空")
    private String skuId;
    @NotNull(message = "商品sku不能为空")
    @Valid
    private List<AttrItemVO> skuList;
    @NotEmpty(message = "商品sku图片不能为空")
    private String pic;
    @NotNull(message = "商品原始价格不能为空")
    private BigDecimal originPrice;
    @NotNull(message = "商品零售价格不能为空")
    private BigDecimal marketPrice;
    @NotNull(message = "sku状态不能为空")
    private Byte status;
    @NotNull(message = "商品库存不能为空")
    private BigDecimal stock;
    @NotNull(message = "库存预警值不能为空")
    private BigDecimal stockAlarm;
}
