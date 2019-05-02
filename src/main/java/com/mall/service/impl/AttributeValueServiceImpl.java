package com.mall.service.impl;

import com.mall.entity.Attribute;
import com.mall.entity.AttributeValue;
import com.mall.entity.Brand;
import com.mall.repository.AttributeRepository;
import com.mall.repository.AttributeValueRepository;
import com.mall.service.AttributeValueService;
import com.mall.util.BeanUtil;
import com.mall.util.DateUtil;
import com.mall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (attributeValue.getId() == null || attributeValue.getId() < 0) {
            attributeValue.setCreateTime(DateUtil.getCurrentDateTime());
            attributeValue.setUpdateTime(DateUtil.getCurrentDateTime());
            attributeValueRepository.save(attributeValue);
            return new Result(0, "success");
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
        return new Result(0, "success");
    }

    @Override
    public Result getAll() {
        List<AttributeValue> attributes = attributeValueRepository.findAll();
        return new Result(0, "success", attributes);
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
        return new Result(0, "success", attributeValues);
    }

    @Override
    public Result delete(Long id) {
        if (!attributeValueRepository.existsById(id)) {
            return new Result(1, "属性值不存在！");
        }
        attributeValueRepository.deleteById(id);
        return new Result(0, "success");
    }
}
