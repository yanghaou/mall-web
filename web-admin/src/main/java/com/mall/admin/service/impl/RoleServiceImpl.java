package com.mall.admin.service.impl;

import com.mall.admin.dto.RoleMenuVO;
import com.mall.admin.service.RoleService;
import com.mall.common.entity.Role;
import com.mall.common.entity.Role;
import com.mall.common.entity.RoleMenu;
import com.mall.common.repository.RoleMenuRepository;
import com.mall.common.repository.RoleRepository;
import com.mall.common.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @Override
    public Result save(Role role) {
        //新增
        if (role.getId() == null || role.getId() < 1) {
            role.setCreateTime(DateUtil.getCurrentDateTime());
            role.setUpdateTime(DateUtil.getCurrentDateTime());
            List<Role> roles = roleRepository.findByRoleName(role.getRoleName());
            if (roles.size() > 0){
                return new Result(1,"角色名称已存在");
            }
            roleRepository.save(role);
            return new Result(RspCode.SUCCESS);
        }
        //编辑
        Optional<Role> roleOptional = roleRepository.findById(role.getId());
        if (!roleOptional.isPresent()) {
            //id不存在
            return new Result(1, "角色不存在");
        }
        role.setUpdateTime(new Date());
        Role attributeSource = roleOptional.get();
        BeanUtil.copyNullProperties(attributeSource, role);
        roleRepository.save(role);
        return new Result(RspCode.SUCCESS);
    }

    @Override
    public Result queryByRole(Role role){
        List<Role> categories = roleRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (role.getId() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"),role.getId())));
            }
            if (StringUtils.isNotEmpty(role.getRoleName())){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("roleName"),"%"+role.getRoleName()+"%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });

        return new Result(RspCode.SUCCESS,new PageResult<>(0,categories));
    }
    
    @Override
    public Result queryByRoleWithPage(PageInfoUtil<Role> vo){
        Pageable pageable = PageRequest.
                of(vo.getPage(),vo.getPageSize(), Sort.by(Sort.Direction.DESC,"updateTime"));
        Page<Role> page = roleRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Role role = vo.getInfo();
            if (role != null) {
                if (role.getId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), role.getId())));
                }
                if (StringUtils.isNotEmpty(role.getRoleName())) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("roleName"), "%" + role.getRoleName() + "%")));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        },pageable);

        PageResult<Role> pageResult = new PageResult<>(page.getTotalElements(),page.getContent());
        return new Result(RspCode.SUCCESS, pageResult);

    }

    @Override
    public Result delete(Long id) {
        if (!roleRepository.existsById(id)) {
            return new Result(1, "角色不存在！");
        }
        roleRepository.deleteById(id);
        //删除关联关系
        roleMenuRepository.deleteByRoleId(id);
        return new Result(RspCode.SUCCESS);
    }


    @Override
    public Result saveRoleMenu(RoleMenuVO roleMenuVO) {
        //先删除旧的roleMenu，再添加新的关联
        roleMenuRepository.deleteByRoleId(roleMenuVO.getRoleId());
        List<RoleMenu> roleMenus = roleMenuVO.getMenuList().stream().map(aLong -> new RoleMenu(roleMenuVO.getRoleId(),aLong)).collect(Collectors.toList());
        roleMenuRepository.saveAll(roleMenus);
        return new Result(RspCode.SUCCESS);
    }
}
