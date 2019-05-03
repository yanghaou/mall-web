package com.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
