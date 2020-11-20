package com.imooc.util;

import com.imooc.vo.ResultVO;

/**
 * 结果视图工具类
 *
 * @author yangxin
 * 2019/06/09 11:32
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ResultVOUtil {

    /**
     * 成功
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return resultVO;
    }

    /**
     * 成功，无参方法
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 错误
     * @param code 错误码
     * @param message 错误信息
     */
    public static ResultVO error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }
}
