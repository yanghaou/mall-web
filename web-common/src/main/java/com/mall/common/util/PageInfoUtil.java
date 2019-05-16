package com.mall.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * author :y.hao
 * time :2019/4/10
 * function:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoUtil<T> {
    @NotNull
    private Integer page;
    @NotNull
    private Integer pageSize;
    T Info;
}
