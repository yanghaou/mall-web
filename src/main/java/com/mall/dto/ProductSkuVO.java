package com.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuVO implements Serializable{
    @NotNull(message = "商品id不能为空")
    private Long id;
    @NotNull(message = "skuStockList不能为空")
    @Valid
    private List<SkuStockVO> skuStockList;
    private List<AttrItemVO> spuList;
}
