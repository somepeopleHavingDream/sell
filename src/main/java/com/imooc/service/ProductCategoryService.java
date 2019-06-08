package com.imooc.service;

import com.imooc.bean.ProductCategory;

import java.util.List;

/**
 * 类目服务类
 *
 * @author yangxin
 * 2019/06/08 11:44
 */
public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);
}
