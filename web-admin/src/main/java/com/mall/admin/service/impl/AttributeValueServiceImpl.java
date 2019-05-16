package com.mall.admin.service.impl;

import com.mall.common.util.BeanUtil;
import com.mall.common.entity.Attribute;
import com.mall.common.entity.AttributeValue;
import com.mall.common.repository.AttributeRepository;
import com.mall.common.repository.AttributeValueRepository;
import com.mall.admin.service.AttributeValueService;
import com.mall.common.util.*;
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

@Service
public class AttributeValueServiceImpl implements AttributeValueService {
    @Autowired
    private AttributeValueRepository attributeValueRepository;
    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public Result save(AttributeValue attributeValue) {
        Optional<Attribute> attributeOptional = attributeRepository.findById(attributeValue.getAttributeId());
        if (!attributeOptional.isPresent()) {
            return new Result(1, "属性不存在");
        }
        //新增
        if (attributeValue.getId() == null || attributeValue.getId() < 1) {
            attributeValue.setCreateTime(DateUtil.getCurrentDateTime());
            attributeValue.setUpdateTime(DateUtil.getCurrentDateTime());
            attributeValueRepository.save(attributeValue);
            return new Result(RspCode.SUCCESS);
        }

        //编辑
        Optional<AttributeValue> valueOptional = attributeValueRepository.findById(attributeValue.getId());
        if (!valueOptional.isPresent()) {
            //id不存在
            return new Result(1, "属性值不存在");
        }
        attributeValue.setUpdateTime(new Date());
        AttributeValue attributeSource = valueOptional.get();
        //把不为空的值复制到更新后的对象
        BeanUtil.copyNullProperties(attributeSource, attributeValue);
        attributeValueRepository.save(attributeValue);
        return new Result(RspCode.SUCCESS);
    }

    @Override
    public Result getAll() {
        List<AttributeValue> attributes = attributeValueRepository.findAll();
        return new Result(RspCode.SUCCESS, attributes);
    }

    @Override
    public Result queryByAttributeValue(final AttributeValue attributeValue){
        List<AttributeValue> attributeValues = attributeValueRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (attributeValue.getId() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"),attributeValue.getId())));
            }
            if (attributeValue.getAttributeId() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("attributeId"),attributeValue.getAttributeId())));
            }
            if (StringUtils.isNotEmpty(attributeValue.getValue())){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("value"),"%"+attributeValue.getValue()+"%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        return new Result(RspCode.SUCCESS, new PageResult<>(0,attributeValues));
    }

    @Override
    public Result queryByAttributeValueWithPage(PageInfoUtil<AttributeValue> vo){
        Pageable pageable = PageRequest.
                of(vo.getPage(),vo.getPageSize(), Sort.by(Sort.Direction.DESC,"updateTime"));
        Page<AttributeValue> page = attributeValueRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            AttributeValue attributeValue = vo.getInfo();
            if (attributeValue != null) {
                if (attributeValue.getId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), attributeValue.getId())));
                }
                if (attributeValue.getAttributeId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("attributeId"), attributeValue.getAttributeId())));
                }
                if (StringUtils.isNotEmpty(attributeValue.getValue())){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("value"),"%"+attributeValue.getValue()+"%")));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        },pageable);

        PageResult<AttributeValue> pageResult = new PageResult<>(page.getTotalElements(),page.getContent());
        return new Result(RspCode.SUCCESS, pageResult);

    }
    
    @Override
    public Result delete(Long id) {
        if (!attributeValueRepository.existsById(id)) {
            return new Result(1, "属性值不存在！");
        }
        attributeValueRepository.deleteById(id);
        return new Result(RspCode.SUCCESS);
    }
}
