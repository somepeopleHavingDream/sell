package com.imooc.util;

/**
 * 数学工具类
 *
 * @author yangxin
 * 2019/10/29 14:38
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较两个Double类型的值是否相等，考虑到精度问题
     */
    public static Boolean equals(Double d1, Double d2) {
        return Math.abs(d1 - d2) < MONEY_RANGE;
    }
}
