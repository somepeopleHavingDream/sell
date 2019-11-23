package com.imooc.repository;

import com.imooc.bean.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *卖家信息
 *
 * @author yangxin
 * 2019/11/23 20:12
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    /**
     * 通过openid获得卖家记录
     *
     * @param openid openid
     * @return 卖家记录
     */
    SellerInfo findByOpenid(String openid);
}
