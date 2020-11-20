package com.imooc.service.impl;

import com.imooc.bean.SellerInfo;
import com.imooc.repository.SellerInfoRepository;
import com.imooc.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 卖家端
 *
 * @author yangxin
 * 2019/11/23 22:30
 */
@Service
public class SellerServiceImpl implements SellerService {

    private final SellerInfoRepository sellerInfoRepository;

    @Autowired
    public SellerServiceImpl(SellerInfoRepository sellerInfoRepository) {
        this.sellerInfoRepository = sellerInfoRepository;
    }

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
