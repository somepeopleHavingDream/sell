package com.imooc.repository;

import com.imooc.bean.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单主表dao类
 *
 * @author yangxin
 * 2019/06/16 19:54
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 通过买家openid分页查询
     *
     * @param buyerOpenId 买家openid
     * @param pageable 分页查询对象
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
