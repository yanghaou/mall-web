package com.mall.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeVO implements Serializable {
    private Long id;
    private String name;
    private Byte sale;
    private Byte orderNum;
    private Byte multi;
    List<AttributeValueVO> values;
}
