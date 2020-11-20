package com.imooc.controller.buyer;

import com.imooc.bean.ProductCategory;
import com.imooc.bean.ProductInfo;
import com.imooc.service.ProductCategoryService;
import com.imooc.service.ProductService;
import com.imooc.util.ResultVOUtil;
import com.imooc.vo.ProductInfoVO;
import com.imooc.vo.ProductVO;
import com.imooc.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 买家商品控制类
 *
 * @author yangxin
 * 2019/06/08 16:05
 */
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    @Autowired
    public BuyerProductController(ProductService productService, ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    /**
     * 列出所有上架商品
     */
    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "123")
    public ResultVO list() {
        // 1. 查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        log.info("productInfoList.size: [{}]", productInfoList.size());

        // 2. 查询类目（一次性查询）
        // 精简方法(java8, lambda)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        log.info("categoryTypeList.size: [{}], productCategoryList.size: [{}]",
                categoryTypeList.size(), productCategoryList.size());

        // 3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = ProductVO.builder()
                    .categoryType(productCategory.getCategoryType())
                    .categoryName(productCategory.getCategoryName())
                    .build();

            // 构建productInfoVOList
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (Objects.equals(productInfo.getCategoryType(), productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
