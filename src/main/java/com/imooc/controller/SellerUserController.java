package com.imooc.controller;

import com.imooc.bean.SellerInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家用户
 *
 * @author yangxin
 * 2019/12/24 17:32
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {
    private final SellerService sellerService;

    @Autowired
    public SellerUserController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    /**
     * 登录
     *
     * @param openid openid
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid, Map<String, Object> map) {
        // openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error");
        }

        // 设置token至redis
        // 设置token至cookie
        return null;
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public void logout() {

    }
}
