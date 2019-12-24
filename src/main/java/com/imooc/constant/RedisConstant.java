package com.imooc.constant;

/**
 * redis常量
 *
 * @author yangxin
 * 2019/12/24 21:40
 */
public interface RedisConstant {
    /**
     * token前缀
     */
    String TOKEN_PREFIX = "token_%s";

    /**
     * 有效时间，2小时
     */
    Integer EXPIRE = 7200;
}
