package com.imooc.exception;

import com.imooc.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常类
 *
 * @author yangxin
 * 2019/06/18 13:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SellException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
