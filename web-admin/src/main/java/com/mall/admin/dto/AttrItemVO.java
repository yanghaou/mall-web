package com.mall.admin.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttrItemVO implements Serializable{
    @NotNull(message = "选择的属性不能为空")
    private Long attributeNameId;
    @NotNull(message = "选择的属性值不能为空")
    private Long attributeValueId;
}
