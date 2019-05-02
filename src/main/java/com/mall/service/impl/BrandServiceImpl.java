package com.mall.service.impl;

import com.mall.entity.Brand;
import com.mall.entity.Category;
import com.mall.repository.BrandRepository;
import com.mall.service.BrandService;
import com.mall.util.BeanUtil;
import com.mall.util.DateUtil;
import com.mall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.mall.util.DateUtil.DATE_PATTERN;

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
        if (brand.getId() == null || brand.getId() < 0) {
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
        return new Result(0, "success", brands);
    }

    public Result delete(Long id) {
        if (!brandRepository.existsById(id)) {
            return new Result(1, "品牌不存在！");
        }
        brandRepository.deleteById(id);
        return new Result(0, "success");
    }
}
