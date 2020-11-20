package com.imooc.util;

import java.util.Random;

/**
 * Key工具类
 *
 * @author yangxin
 * 2019/06/18 14:09
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     */
    public static synchronized String generateUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
