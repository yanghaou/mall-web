package com.mall.api.service.impl;

import com.mall.api.service.CategoryService;
import com.mall.common.entity.Category;
import com.mall.common.repository.CategoryRepository;
import com.mall.common.util.PageResult;
import com.mall.common.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Result queryByCategory(Category category){
        List<Category> categories = categoryRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (category.getId() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"),category.getId())));
            }
            if (category.getParentId() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("parentId"),category.getParentId())));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });

        return new Result(0,"success",categories);
    }

}
