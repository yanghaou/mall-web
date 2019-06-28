package com.mall.admin.controller;

import com.mall.admin.dto.RoleMenuVO;
import com.mall.admin.service.RoleService;
import com.mall.admin.service.CommonService;
import com.mall.common.entity.Role;
import com.mall.common.util.PageInfoUtil;
import com.mall.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
@RestController
public class RoleController extends BaseController{
    @Autowired
    private RoleService roleService;

    @PostMapping("/api/role")
    public Result save(@Valid @RequestBody Role role, BindingResult bindingResult) {
        Result result = checkParam(bindingResult);
        if (result != null) {
            return result;
        }
        return roleService.save(role);
    }

    @PostMapping("/api/role/menu")
    public Result saveRoleMenu(@Valid @RequestBody RoleMenuVO roleMenuVO, BindingResult bindingResult) {
        Result result = checkParam(bindingResult);
        if (result != null) {
            return result;
        }
        return roleService.saveRoleMenu(roleMenuVO);
    }


    @GetMapping("/api/role")
    public Result queryRole(@RequestParam(value = "id",required = false) Long id,
                                @RequestParam(value = "roleName",required = false) String roleName,
                                @RequestParam(value = "page",required = false) Integer page,
                                @RequestParam(value = "pageSize",required = false) Integer pageSize) {
        Role role = Role.builder().id(id).roleName(roleName).build();
        if (page != null && pageSize != null){
            PageInfoUtil<Role> pageInfoUtil = new PageInfoUtil<>(page-1<0?0:page-1,pageSize,role);
            return roleService.queryByRoleWithPage(pageInfoUtil);
        }
        return roleService.queryByRole(role);
    }

    @DeleteMapping("/api/role/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return roleService.delete(id);
    }
}
