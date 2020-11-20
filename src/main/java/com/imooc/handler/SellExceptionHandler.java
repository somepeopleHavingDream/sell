package com.imooc.handler;

import com.imooc.config.ProjectUrlConfig;
import com.imooc.exception.SellException;
import com.imooc.exception.SellerAuthorizeException;
import com.imooc.util.ResultVOUtil;
import com.imooc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 微信点餐
 *
 * @author yangxin
 * 2019/12/29 13:01
 */
@SuppressWarnings("rawtypes")
@ControllerAdvice
public class SellExceptionHandler {

    private final ProjectUrlConfig projectUrlConfig;

    @Autowired
    public SellExceptionHandler(ProjectUrlConfig projectUrlConfig) {
        this.projectUrlConfig = projectUrlConfig;
    }

    /**
     * 拦截登录异常
     */
    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView handleAuthorizeException() {
        return new ModelAndView("redirect:"
            + projectUrlConfig.getWechatOpenAuthorize()
            + "/sell/wechat/qrAuthorize"
            + "?returnUrl="
            + projectUrlConfig.getSell()
            + "/sell/seller/login");
    }

    /**
     * 处理普通异常
     */
    @ExceptionHandler(SellException.class)
    @ResponseBody
    public ResultVO handleSellException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
