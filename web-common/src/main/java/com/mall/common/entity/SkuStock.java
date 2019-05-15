package com.mall.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_sku_stock")
public class SkuStock implements Serializable {
    @Id
    private String skuId;
    private Long productId;
    private String pic;
    private BigDecimal originPrice;
    private BigDecimal marketPrice;
    private BigDecimal stock;
    private BigDecimal stockAlarm;
    private Date createTime;
    private Date updateTime;

}
