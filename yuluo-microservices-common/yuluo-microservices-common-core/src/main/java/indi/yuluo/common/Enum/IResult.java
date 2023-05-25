package indi.yuluo.common.Enum;

/**
 * @author: yuluo
 * @date: 2023/5/15 18:21
 * @description: 返回结果行为接口
 */

public interface IResult {

    /**
     * 获取code
     * @return
     */
    Integer getCode();

    /**
     * 获取描述
     * @return
     */
    String getMessage();

}
