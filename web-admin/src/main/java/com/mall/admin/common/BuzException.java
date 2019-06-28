package com.mall.admin.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuzException extends RuntimeException {
    private Integer code;
    private String msg;
}
