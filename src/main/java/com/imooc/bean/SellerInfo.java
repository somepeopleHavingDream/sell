package com.imooc.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 卖家信息
 *
 * @author yangxin
 * 2019/11/23 22:08
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerInfo {
    /**
     * 卖家Id
     */
    @Id
    private String sellerId;

    /**
     * 卖家名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * openid
     */
    private String openid;
}
