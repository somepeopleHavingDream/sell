package com.imooc.vo;

import lombok.Data;

/**
 * Http请求返回的最外层对象
 *
 * @author yangxin
 * 2019/06/08 16:10
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
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
