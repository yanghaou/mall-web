package com.mall.admin.service.impl;

import com.mall.admin.service.MenuService;
import com.mall.admin.util.Tree;
import com.mall.admin.util.TreeUtils;
import com.mall.common.entity.Menu;
import com.mall.common.repository.MenuRepository;
import com.mall.common.util.BeanUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.util.Result;
import com.mall.common.util.RspCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.mall.admin.constant.ConstantParam.TYPE_BUTTON;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    private boolean checkButton(Menu menu){
        if (menu.getType().equals(TYPE_BUTTON)){
            //按钮,uri和value都不能为空
            if (StringUtils.isEmpty(menu.getUri()) || StringUtils.isEmpty(menu.getValue())){
                return false;
            }
        }
        return true;
    }

    @Override
    public Result save(Menu menu) {
        if (!checkButton(menu)){
            return new Result(RspCode.FAILED,"按钮的权限值和url路径都不能为空");
        }
        //新增
        if (menu.getId() == null || menu.getId() < 1) {
            menu.setCreateTime(DateUtil.getCurrentDateTime());
            menu.setUpdateTime(DateUtil.getCurrentDateTime());
            menuRepository.save(menu);
            return new Result(RspCode.SUCCESS);
        }
        //编辑
        Optional<Menu> menuOptional = menuRepository.findById(menu.getId());
        if (!menuOptional.isPresent()) {
            //id不存在
            return new Result(1, "菜单不存在");
        }
        menu.setUpdateTime(new Date());
        Menu source = menuOptional.get();
        BeanUtil.copyNullProperties(source, menu);
        menuRepository.save(menu);
        return new Result(RspCode.SUCCESS);
    }

    @Override
    public Result query(Menu menu){
        return new Result(RspCode.SUCCESS,queryByMenu(menu));
    }

    @Override
    public Result getTree(){
        return new Result(RspCode.SUCCESS,getAllMenusTree());
    }

    private Tree<Menu> getAllMenusTree(){
        List<Menu> menus = menuRepository.findAll();
        List<Tree<Menu>> trees = new ArrayList<>();
        menus.forEach(menu -> {
            Tree<Menu> tree = new Tree<>();
            tree.setId(menu.getId());
            tree.setParentId(menu.getPid());
            tree.setName(menu.getName());
            tree.setIcon(menu.getIcon());
            trees.add(tree);
        });
        return TreeUtils.build(trees);
    }

    private List<Menu> queryByMenu(Menu menu){
        List<Menu> menus = menuRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (menu.getId() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"),menu.getId())));
            }
            if (menu.getType() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("type"),menu.getType())));
            }
            if (StringUtils.isNotEmpty(menu.getName())){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"),"%"+menu.getName()+"%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        return menus;
    }

}
