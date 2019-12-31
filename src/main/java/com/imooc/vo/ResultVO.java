package com.imooc.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Http请求返回的最外层对象
 *
 * @author yangxin
 * 2019/06/08 16:10
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -2113974960753084198L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 具体内容
     */
    private T data;
}
