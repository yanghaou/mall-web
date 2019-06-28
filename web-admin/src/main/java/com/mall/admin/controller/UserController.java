package com.mall.admin.controller;

import com.mall.admin.service.CommonService;
import com.mall.admin.service.UserService;
import com.mall.common.entity.User;
import com.mall.common.util.Result;
import com.mall.common.util.RspCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @PostMapping("/api/register")
    public Result register(@RequestBody @Valid User user, BindingResult bindingResult){
        Result result = checkParam(bindingResult);
        if (result != null){
            return result;
        }
        return userService.register(user);
    }

    @PostMapping("/api/login")
    public Result login(@RequestBody @Valid User user, BindingResult bindingResult){
        Result result = checkParam(bindingResult);
        if (result != null){
            return result;
        }
        return userService.login(user);
    }

    @GetMapping("/api/logout")
    public Result logout(){
        return new Result(RspCode.SUCCESS);
    }

}
