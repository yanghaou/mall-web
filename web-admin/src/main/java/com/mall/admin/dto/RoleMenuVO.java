package com.mall.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuVO implements Serializable{
    @NotNull(message = "角色id不能为空")
    Long roleId;
    @NotNull(message = "角色分配的权限不能为空")
    List<Long> menuList;
}
