package com.mall.admin.service.impl;

import com.mall.admin.service.UserService;
import com.mall.common.entity.Menu;
import com.mall.common.entity.User;
import com.mall.common.repository.MenuRepository;
import com.mall.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Menu> getMenuList(Long id) {
        return menuRepository.find;
    }
}
