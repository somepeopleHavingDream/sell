package com.imooc.repository;

import com.imooc.bean.SellerInfo;
import com.imooc.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 卖家信息
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save() {
        SellerInfo sellerInfo = SellerInfo.builder()
                .sellerId(KeyUtil.generateUniqueKey())
                .username("admin")
                .password("admin")
                .openid("abc")
                .build();

        SellerInfo result = sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenid() {
        SellerInfo result = sellerInfoRepository.findByOpenid("abc");
        Assert.assertEquals("abc", result.getOpenid());
    }
}