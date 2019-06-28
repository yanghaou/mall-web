package com.mall.admin.service;

import com.mall.common.entity.User;
import com.mall.common.util.Result;

public interface UserService {

    Result register(User user);

    Result login(User user);
}
