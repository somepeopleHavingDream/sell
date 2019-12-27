package com.imooc.controller;

import com.imooc.bean.SellerInfo;
import com.imooc.config.ProjectUrlConfig;
import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.enums.ResultEnum;
import com.imooc.service.SellerService;
import com.imooc.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户
 *
 * @author yangxin
 * 2019/12/24 17:32
 */
@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {
    private final SellerService sellerService;
    private final StringRedisTemplate redisTemplate;
    private final ProjectUrlConfig projectUrlConfig;

    @Autowired
    public SellerUserController(SellerService sellerService, StringRedisTemplate redisTemplate, ProjectUrlConfig projectUrlConfig) {
        this.sellerService = sellerService;
        this.redisTemplate = redisTemplate;
        this.projectUrlConfig = projectUrlConfig;
    }

    /**
     * 登录
     *
     * @param openid openid
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        log.info("openid: [{}]", openid);

        // openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error");
        }

        // 设置token至redis
        String token = UUID.randomUUID().toString();
        int expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue()
                .set(String.format(RedisConstant.TOKEN_PREFIX, token),
                        openid,
                        expire,
                        TimeUnit.SECONDS);

        // 设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        // 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            // 清除redis
            redisTemplate.opsForValue()
                    .getOperations()
                    .delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            // 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
