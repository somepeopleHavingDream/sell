package com.imooc.controller;

import com.imooc.vo.ProductInfoVO;
import com.imooc.vo.ProductVO;
import com.imooc.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * 买家商品控制类
 *
 * @author yangxin
 * 2019/06/08 16:05
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @GetMapping("/list")
    public ResultVO list() {
        ResultVO<Object> resultVO = new ResultVO<>();
        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();

        productVO.setProductInfoVOList(Collections.singletonList(productInfoVO));
        resultVO.setData(Collections.singletonList(productVO));
        resultVO.setCode(0);
        resultVO.setMessage("成功");

        return resultVO;
    }
}
