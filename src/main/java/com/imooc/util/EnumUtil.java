package com.imooc.util;

import com.imooc.enums.CodeEnum;

import java.util.Objects;

/**
 * 枚举工具类
 *
 * @author yangxin
 * 2019/10/30 15:58
 */
public class EnumUtil {
    /**
     * 通过状态值获得枚举对象
     */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T e : enumClass.getEnumConstants()) {
            if (Objects.equals(e.getCode(), code)) {
                return e;
            }
        }
        return null;
    }
}
