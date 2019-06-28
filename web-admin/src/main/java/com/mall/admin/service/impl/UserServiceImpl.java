package com.mall.admin.service.impl;

import com.google.common.collect.Maps;
import com.mall.admin.config.JwtTokenUtil;
import com.mall.admin.service.UserService;
import com.mall.common.entity.User;
import com.mall.common.repository.MenuRepository;
import com.mall.common.repository.UserRepository;
import com.mall.common.util.Result;
import com.mall.common.util.RspCode;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Result register(User user){
        User old = userRepository.findByUsername(user.getUsername());
        if (old != null){
            return new Result(RspCode.USER_EXIST);
        }
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userRepository.save(user);
        return new Result(RspCode.SUCCESS);
    }

    @Override
    public Result login(User user){
        User loginUser = userRepository.findByUsername(user.getUsername());
        if (loginUser == null){
            return new Result(RspCode.USER_NOT_EXIST);
        }
        if (!BCrypt.checkpw(user.getPassword(),loginUser.getPassword())){
            return new Result(RspCode.PASSWORD_FAILED);
        }

        String token = jwtTokenUtil.generateToken(user);
        Map<String,String> map = Maps.newHashMap();
        map.put("token",token);
        map.put("tokenHead",tokenHead);
        //缓存一个token，用来做退出
        redisTemplate.opsForValue().set("token_"+user.getUsername(),token,1, TimeUnit.HOURS);
        return new Result(map);
    }
}
