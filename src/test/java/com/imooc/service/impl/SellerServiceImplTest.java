package com.imooc.service.impl;

import com.imooc.bean.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 卖家端
 *
 * @author yangxin
 * 2019/11/23 22:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {
    /**
     * openid
     */
    private static final String OPENID = "abc";

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo result = sellerService.findSellerInfoByOpenid(OPENID);
        Assert.assertEquals(OPENID, result.getOpenid());
    }
}