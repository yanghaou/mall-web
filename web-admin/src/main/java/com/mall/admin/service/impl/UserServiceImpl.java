package com.mall.admin.service.impl;

import com.mall.admin.service.UserService;
import com.mall.common.entity.Menu;
import com.mall.common.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public List<Menu> getPermissionList(Long id) {
        return null;
    }
}
