package com.mall.admin.service;

import com.mall.common.entity.Menu;
import com.mall.common.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<Menu> getPermissionList(Long id);
}
