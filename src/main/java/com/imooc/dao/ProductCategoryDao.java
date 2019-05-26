package com.imooc.dao;

import com.imooc.bean.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yangxin
 * 2019/05/26 14:56
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {
}
