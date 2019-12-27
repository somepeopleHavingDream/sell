package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/**
 * 卖家授权
 *
 * @author yangxin
 * 2019/12/27 17:26
 */
public class SellerAuthorizeException extends RuntimeException {
    /**
     * 状态码
     */
    private Integer code;

    public SellerAuthorizeException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellerAuthorizeException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
