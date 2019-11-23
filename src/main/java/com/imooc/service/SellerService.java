package com.imooc.service;

import com.imooc.bean.SellerInfo;

/**
 * 卖家端
 *
 * @author yangxin
 * 2019/11/23 22:29
 */
public interface SellerService {
    /**
     * 通过openid查询卖家端信息
     *
     * @param openid openid
     * @return 卖家端对象
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
