package com.imooc.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 根据名字，获取cookie
     */
    public static Cookie get(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = buildCookieName2Cookie(request);
        return cookieMap.getOrDefault(name, null);
    }

    /**
     * 构建cookie名对cookie的映射关系
     */
    private static Map<String, Cookie> buildCookieName2Cookie(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();

        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return Collections.emptyMap();
        }

        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie);
        }

        return cookieMap;
    }
}
