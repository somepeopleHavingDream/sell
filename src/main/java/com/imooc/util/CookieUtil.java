package com.imooc.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie
 *
 * @author yangxin
 * 2019/12/24 22:21
 */
public class CookieUtil {
    /**
     * 设置cookie
     */
    public static void set(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void get() {

    }
}
