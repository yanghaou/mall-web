package com.mall.admin.service.impl;

import com.mall.admin.dto.AttributeVO;
import com.mall.admin.dto.AttributeValueVO;
import com.mall.common.entity.Attribute;
import com.mall.common.entity.AttributeValue;
import com.mall.common.entity.Category;
import com.mall.admin.repository.AttributeRepository;
import com.mall.admin.repository.AttributeValueRepository;
import com.mall.admin.repository.CategoryRepository;
import com.mall.admin.service.AttributeService;
import com.mall.admin.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
import java.util.stream.Collectors;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private AttributeValueRepository attributeValueRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Result save(Attribute attribute) {
        Optional<Category> categoryOptional = categoryRepository.findById(attribute.getCategoryId());
        if (!categoryOptional.isPresent()) {
            return new Result(1, "所属分类不存在");
        }
        //新增
        if (attribute.getId() == null || attribute.getId() < 1) {
            attribute.setCreateTime(DateUtil.getCurrentDateTime());
            attribute.setUpdateTime(DateUtil.getCurrentDateTime());
            attributeRepository.save(attribute);
            return new Result(0, "success");
        }

        //编辑
        Optional<Attribute> attributeOptional = attributeRepository.findById(attribute.getId());
        if (!attributeOptional.isPresent()) {
            //id不存在
            return new Result(1, "类型不存在");
        }
        attribute.setUpdateTime(new Date());
        Attribute attributeSource = attributeOptional.get();
        BeanUtil.copyNullProperties(attributeSource, attribute);
        attributeRepository.save(attribute);
        return new Result(0, "success");
    }

    @Override
    public Result queryByAttribute(Attribute attribute){
        List<Attribute> attributes = attributeRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (attribute.getId() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"),attribute.getId())));
            }
            if (attribute.getCategoryId() != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("categoryId"),attribute.getCategoryId())));
            }

            if (StringUtils.isNotEmpty(attribute.getName())){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"),"%"+attribute.getName()+"%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        });

        return new Result(0,"success",new PageResult<>(0,attributes));
    }

    @Override
    public Result queryByAttributeWithPage(PageInfoUtil<Attribute> vo){
        Pageable pageable = PageRequest.
                of(vo.getPage(),vo.getPageSize(), Sort.by(Sort.Direction.DESC,"updateTime"));
        Page<Attribute> page = attributeRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Attribute attribute = vo.getInfo();
            if (attribute != null) {
                if (attribute.getId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), attribute.getId())));
                }
                if (attribute.getCategoryId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("categoryId"), attribute.getCategoryId())));
                }
                if (StringUtils.isNotEmpty(attribute.getName())) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + attribute.getName() + "%")));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        },pageable);

        PageResult<Attribute> pageResult = new PageResult<>(page.getTotalElements(),page.getContent());
        return new Result(0, "success", pageResult);

    }

    @Override
    public Result queryAttributeList(Long categoryId){
        List<Attribute> attributes = attributeRepository.findByCategoryIdOrderByOrderNumDesc(categoryId);
        List<AttributeValue> attributeValues = attributeValueRepository
                .findByAttributeIdInOrderByOrderNumDesc(
                        attributes.stream().map(Attribute::getId)
                                .collect(Collectors.toList()));
        List<AttributeVO> attributeVOS = attributes.stream().map(attribute -> {
            AttributeVO attributeVO = new AttributeVO();
            BeanUtils.copyProperties(attribute,attributeVO);
            return attributeVO;
        }).map(attributeVO -> {
            List<AttributeValueVO> values = attributeValues.stream()
                    .filter(attributeValue -> attributeVO.getId().equals(attributeValue.getAttributeId()))
                    .map(attributeValue -> {
                AttributeValueVO attributeValueVO = new AttributeValueVO();
                BeanUtils.copyProperties(attributeValue,attributeValueVO);
                return attributeValueVO;
            }).collect(Collectors.toList());
            attributeVO.setValues(values);
            return attributeVO;
        }).collect(Collectors.toList());

        return new Result(0,"success",attributeVOS);
    }

    @Override
    public Result delete(Long id) {
        if (!attributeRepository.existsById(id)) {
            return new Result(1, "类型不存在！");
        }
        attributeRepository.deleteById(id);
        return new Result(0, "success");
    }
}
