package com.imooc.dao;

import com.imooc.bean.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangxin
 * 2019/05/26 15:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = productCategoryDao.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = productCategoryDao.findOne(2);
        productCategory.setCategoryType(10);
        productCategoryDao.save(productCategory);
    }
}