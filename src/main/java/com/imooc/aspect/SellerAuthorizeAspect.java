package com.imooc.aspect;

import com.imooc.constant.CookieConstant;
import com.imooc.exception.SellerAuthorizeException;
import com.imooc.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 卖家授权
 *
 * @author yangxin
 * 2019/12/27 17:17
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    /**
     * 校验
     */
    @Pointcut("execution(public * com.imooc.controller.Seller*.*(..))"
    + "&& !execution(public * com.imooc.controller.SellerUserController.*(..))")
    public void verify() {}

    /**
     * 校验之前的事
     */
    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            // todo
            throw new SellerAuthorizeException(null);
        }
    }
}
