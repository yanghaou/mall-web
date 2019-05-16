package com.mall.admin.service.impl;

import com.mall.admin.dto.AttrItemVO;
import com.mall.admin.dto.ProductSkuVO;
import com.mall.admin.dto.ProductVO;
import com.mall.admin.dto.SkuStockVO;
import com.mall.common.util.BeanUtil;
import com.mall.common.entity.Product;
import com.mall.common.entity.Sku;
import com.mall.common.entity.SkuStock;
import com.mall.common.entity.Spu;
import com.mall.common.repository.*;
import com.mall.admin.service.ProductService;
import com.mall.common.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private SkuStockRepository skuStockRepository;
    @Autowired
    private SpuRepository spuRepository;


    //保存和更新
    @Override
    public Result saveProduct(ProductVO vo){
        //校验分类存在
        if (!categoryRepository.existsById(vo.getCategoryId())){
            return new Result(-1, "商品所属分类不存在");
        }
        if (vo.getBrandId() != null && !brandRepository.existsById(vo.getBrandId())){
            return new Result(-1, "品牌不存在");
        }
        Product product = new Product();
        BeanUtils.copyProperties(vo,product);
        //新增商品
        if (product.getId() == null || product.getId() < 1) {
            product.setCreateTime(DateUtil.getCurrentDateTime());
            product.setUpdateTime(DateUtil.getCurrentDateTime());
            productRepository.save(product);
            return new Result(RspCode.SUCCESS);
        }
        //编辑
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (!productOptional.isPresent()) {
            //id不存在
            return new Result(1, "商品不存在");
        }
        product.setUpdateTime(new Date());
        Product pSource = productOptional.get();
        BeanUtil.copyNullProperties(pSource, product);
        productRepository.save(product);
        return new Result(RspCode.SUCCESS);
    }

    @Override
    public Result deleteProduct(Long id){
        productRepository.deleteById(id);
        return new Result(RspCode.SUCCESS);
    }

    @Override
    public Result queryByProduct(PageInfoUtil<Product> vo){
        Pageable pageable = PageRequest.
                of(vo.getPage(),vo.getPageSize(), Sort.by(Sort.Direction.DESC,"updateTime"));
        Page<Product> page = productRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Product product = vo.getInfo();
            if (product != null) {
                if (product.getId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), product.getId())));
                }
                if (product.getCategoryId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("categoryId"), product.getCategoryId())));
                }
                if (product.getBrandId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("brandId"), product.getBrandId())));
                }
                if (StringUtils.isNotEmpty(product.getName())) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + product.getName() + "%")));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        },pageable);

        PageResult<Product> pageResult = new PageResult<>(page.getTotalElements(),page.getContent());
        return new Result(RspCode.SUCCESS, pageResult);

    }

    //保存和更新
    @Override
    public Result saveSku(ProductSkuVO vo){
        //校验商品是否存在
        Optional<Product> productOptional = productRepository.findById(vo.getId());
        if (!productOptional.isPresent()) {
            return new Result(1, "商品不存在");
        }

        //保存sku
        List<Sku> skus = new ArrayList<>();
        List<SkuStock> skuStocks = new ArrayList<>();
        List<Spu> spus = new ArrayList<>();
        Date date = new Date();

        List<SkuStockVO> skuStockVOS = vo.getSkuStockList();
        for (SkuStockVO skuStockVO :skuStockVOS){
            //遍历sku stock
            SkuStock skuStock = SkuStock.builder()
                    .skuId(skuStockVO.getSkuId())
                    .productId(vo.getId())
                    .stock(skuStockVO.getStock())
                    .stockAlarm(skuStockVO.getStockAlarm())
                    .marketPrice(skuStockVO.getMarketPrice())
                    .originPrice(skuStockVO.getOriginPrice())
                    .status(skuStockVO.getStatus())
                    .pic(skuStockVO.getPic())
                    .createTime(date)
                    .updateTime(date)
                    .build();
            skuStocks.add(skuStock);

            //遍历sku
            List<AttrItemVO> attrItems = skuStockVO.getSkuList();
            for (AttrItemVO attrItem : attrItems){
                Sku sku = Sku.builder()
                        .skuId(skuStockVO.getSkuId())
                        .attributeNameId(attrItem.getAttributeNameId())
                        .attributeValueId(attrItem.getAttributeValueId())
                        .productId(vo.getId())
                        .createTime(date)
                        .updateTime(date)
                        .build();
                skus.add(sku);
            }
        }

        //遍历spu
        List<AttrItemVO> spuVOList = vo.getSpuList();
        if (spuVOList != null){
            spuVOList.forEach(attrItemVO -> {
                Spu spu = Spu.builder()
                        .attributeNameId(attrItemVO.getAttributeNameId())
                        .attributeValueId(attrItemVO.getAttributeValueId())
                        .createTime(date)
                        .updateTime(date)
                        .productId(vo.getId())
                        .build();
                spus.add(spu);
            });
        }

        //先删除商品旧的sku skuStock 和spu
        spuRepository.deleteAllByProductId(vo.getId());
        skuRepository.deleteAllByProductId(vo.getId());
        skuStockRepository.deleteAllByProductId(vo.getId());

        //再保存新的
        spuRepository.saveAll(spus);
        skuRepository.saveAll(skus);
        skuStockRepository.saveAll(skuStocks);
        return new Result(RspCode.SUCCESS);
    }

    @Override
    public Result querySkuByProductId(Long productId){
        //查询spu
        List<Spu> spus = spuRepository.findByProductId(productId);
        //查询sku
        List<Sku> skus = skuRepository.findByProductId(productId);
        //根据skuId一次性查询skuStock,然后内存中组合
        List<String> skuIdList = skus.stream().map(Sku::getSkuId).collect(Collectors.toList());
        List<SkuStock> skuStocks = skuStockRepository.findBySkuIdIn(skuIdList);

        List<AttrItemVO> spuList = new ArrayList<>();
        spus.forEach(spu -> {
            AttrItemVO attrItemVO = AttrItemVO
                    .builder()
                    .attributeNameId(spu.getAttributeNameId())
                    .attributeValueId(spu.getAttributeValueId())
                    .build();
            spuList.add(attrItemVO);
        });

        List<SkuStockVO> skuStockVOS = new ArrayList<>();
        skuStocks.forEach(skuStock -> {
            SkuStockVO skuStockVO =new SkuStockVO();
            BeanUtils.copyProperties(skuStock,skuStockVO);
            //从sku中找出skuId对应的组合
            List<AttrItemVO> attrItemVOS = skus.stream()
                    .filter(sku -> sku.getSkuId().equals(skuStock.getSkuId())) //根据skuId过滤sku
                    .map(sku -> new AttrItemVO(sku.getAttributeNameId(),sku.getAttributeValueId()))
                    .collect(Collectors.toList()); //将sku转为AttrItemVO
            skuStockVO.setSkuList(attrItemVOS);
            skuStockVOS.add(skuStockVO);
        });

        ProductSkuVO productSkuVO = new ProductSkuVO(productId,skuStockVOS,spuList);

        return new Result(RspCode.SUCCESS,productSkuVO);

    }
}
