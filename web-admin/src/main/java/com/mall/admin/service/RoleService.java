package com.mall.admin.service;

import com.mall.admin.dto.RoleMenuVO;
import com.mall.common.entity.Role;
import com.mall.common.util.PageInfoUtil;
import com.mall.common.util.Result;

import javax.validation.Valid;

public interface RoleService {
    Result save(Role role);

    Result queryByRole(Role role);

    Result queryByRoleWithPage(PageInfoUtil<Role> vo);

    Result delete(Long id);

    Result saveRoleMenu(RoleMenuVO roleMenuVO);
}
