package com.mall.controller;

import com.mall.dto.ProductSkuVO;
import com.mall.dto.ProductVO;
import com.mall.entity.Product;
import com.mall.repository.ProductRepository;
import com.mall.service.ProductService;
import com.mall.service.CommonService;
import com.mall.util.PageInfoUtil;
import com.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CommonService commonService;

    @PostMapping("/api/product")
    public Result saveProduct(@Valid @RequestBody ProductVO productVO, BindingResult bindingResult) {
        Result result = commonService.checkParam(bindingResult);
        if (result != null) {
            return result;
        }
        return productService.saveProduct(productVO);
    }

    @GetMapping("/api/product")
    public Result query(@RequestParam(value = "id",required = false) Long id,
                               @RequestParam(value = "name",required = false) String name,
                               @RequestParam(value = "categoryId",required = false) Long categoryId,
                               @RequestParam(value = "brandId",required = false) Long brandId,
                               @RequestParam(value = "page") int page,
                               @RequestParam(value = "pageSize") int pageSize){
        Product product = Product.builder()
                .id(id).name(name).categoryId(categoryId).brandId(brandId).build();

        PageInfoUtil<Product> pageInfoUtil = new PageInfoUtil<>(page-1<0?0:page-1,pageSize,product);
        return productService.queryByProduct(pageInfoUtil);
    }

    @DeleteMapping("/api/product/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }

    @PostMapping("/api/product/sku")
    public Result saveProductSku(@Valid @RequestBody ProductSkuVO productSkuVO, BindingResult bindingResult) {
        Result result = commonService.checkParam(bindingResult);
        if (result != null) {
            return result;
        }
        return productService.saveSku(productSkuVO);
    }

    @GetMapping("/api/product/sku")
    public Result querySkuByProductId(@RequestParam("productId") Long productId){
        return productService.querySkuByProductId(productId);
    }
}
