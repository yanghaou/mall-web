package com.mall.admin.security;

import com.mall.admin.service.UserService;
import com.mall.common.entity.Menu;
import com.mall.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("%s.用户名或密码错误", username));
        }
        List<Menu> menus = userService.getPermissionList(user.getId());
        return new JwtUser(user, menus);
    }
}
