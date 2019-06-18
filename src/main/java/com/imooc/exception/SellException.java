package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/**
 * 业务异常类
 *
 * @author yangxin
 * 2019/06/18 13:59
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
