package com.xinyan.sell.utils;

import com.xinyan.sell.vo.ResultVO;

public class ResultVOUtil {

    private ResultVOUtil(){

    }

    /**
     * 成功：返回数据
     * @param obj
     * @return
     */
    public static ResultVO success(Object obj){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(obj);
        return  resultVO;
    }

    /**
     * 成功：不返回数据
     * @return
     */
    public static ResultVO success(){
        return success(null);
    }

    /**
     * 错误
     * @param code
     * @param msg
     * @return
     */
    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
