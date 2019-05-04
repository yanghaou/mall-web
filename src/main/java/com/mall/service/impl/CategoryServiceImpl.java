package com.mall.service.impl;

import com.mall.entity.Category;
import com.mall.repository.CategoryRepository;
import com.mall.service.CategoryService;
import com.mall.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Result save(Category category) {
        //新增
        if (category.getId() == null || category.getId() < 1) {
            category.setCreateTime(DateUtil.getCurrentDateTime());
            category.setUpdateTime(DateUtil.getCurrentDateTime());
            categoryRepository.save(category);
            return new Result(0, "success");
        }
        //编辑
        Optional<Category> categoryOptional = categoryRepository.findById(category.getId());
        if (!categoryOptional.isPresent()) {
            //id不存在
            return new Result(1, "类型不存在");
        }
        category.setUpdateTime(new Date());
        Category attributeSource = categoryOptional.get();
        BeanUtil.copyNullProperties(attributeSource, category);
        categoryRepository.save(category);
        return new Result(0, "success");
    }

    @Override
    public Result getAll() {
        List<Category> categories = categoryRepository.findAll();
        return new Result(0, "success", categories);
    }

    @Override
    public Result queryByCategory(Category category){
        List<Category> categories = categoryRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (category.getId() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"),category.getId())));
            }
            if (StringUtils.isNotEmpty(category.getName())){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"),"%"+category.getName()+"%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        });

        return new Result(0,"success",new PageResult<>(0,categories));
    }

    @Override
    public Result queryByCategoryWithPage(PageInfoUtil<Category> vo){
        Pageable pageable = PageRequest.
                of(vo.getPage(),vo.getPageSize(), Sort.by(Sort.Direction.DESC,"updateTime"));
        Page<Category> page = categoryRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Category category = vo.getInfo();
            if (category != null) {
                if (category.getId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), category.getId())));
                }
                if (StringUtils.isNotEmpty(category.getName())) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + category.getName() + "%")));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        },pageable);

        PageResult<Category> pageResult = new PageResult<>(page.getTotalElements(),page.getContent());
        return new Result(0, "success", pageResult);

    }
    
    @Override
    public Result delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            return new Result(1, "分类不存在！");
        }
        categoryRepository.deleteById(id);
        return new Result(0, "success");
    }
}
