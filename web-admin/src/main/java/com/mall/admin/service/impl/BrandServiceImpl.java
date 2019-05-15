package com.mall.admin.service.impl;

import com.mall.admin.entity.Brand;
import com.mall.admin.repository.BrandRepository;
import com.mall.admin.service.BrandService;
import com.mall.admin.util.*;
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
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public Result save(Brand brand) {
        //新增
        if (brand.getId() == null || brand.getId() < 1) {
            brand.setCreateTime(DateUtil.getCurrentDateTime());
            brand.setUpdateTime(DateUtil.getCurrentDateTime());
            brandRepository.save(brand);
            return new Result(0, "success");
        }
        //编辑
        Optional<Brand> brandOptional = brandRepository.findById(brand.getId());
        if (!brandOptional.isPresent()) {
            //id不存在
            return new Result(1, "品牌不存在");
        }
        brand.setUpdateTime(new Date());
        Brand attributeSource = brandOptional.get();
        BeanUtil.copyNullProperties(attributeSource, brand);
        brandRepository.save(brand);
        return new Result(0, "success");
        
    }

    public Result getAll() {
        List<Brand> tBrands = brandRepository.findAll();
        return new Result(0, "success", tBrands);
    }

    @Override
    public Result queryByBrand(final Brand brand){
        List<Brand> brands = brandRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (brand.getId() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"),brand.getId())));
            }
            if (StringUtils.isNotEmpty(brand.getName())){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"),"%"+brand.getName()+"%")));
            }
            if (brand.getUpdateTime() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("updateTime"), brand.getUpdateTime())));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        });
        return new Result(0, "success", new PageResult<>(0,brands));
    }

    @Override
    public Result queryByBrandWithPage(PageInfoUtil<Brand> vo){

        Pageable pageable = PageRequest.
                of(vo.getPage(),vo.getPageSize(), Sort.by(Sort.Direction.DESC,"updateTime"));
        Page<Brand> page = brandRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Brand brand = vo.getInfo();
            if (brand != null) {
                if (brand.getId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), brand.getId())));
                }
                if (StringUtils.isNotEmpty(brand.getName())) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + brand.getName() + "%")));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        },pageable);

        PageResult<Brand> pageResult = new PageResult<>(page.getTotalElements(),page.getContent());
        return new Result(0, "success", pageResult);

    }

    public Result delete(Long id) {
        if (!brandRepository.existsById(id)) {
            return new Result(1, "品牌不存在！");
        }
        brandRepository.deleteById(id);
        return new Result(0, "success");
    }
}
