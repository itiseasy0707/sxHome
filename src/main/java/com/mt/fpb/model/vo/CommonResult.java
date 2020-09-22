package com.mt.fpb.model.vo;


import com.mt.fpb.common.enums.IResponseCode;
import com.mt.fpb.common.enums.ResponseCode;
import lombok.Data;

/**
 * 通用返回结果
 *
 * @author g
 * @date 2019-11-16 11:18
 */
@Data
public class CommonResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    protected CommonResult() {
    }

    protected CommonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param msg  提示信息
     * @param data 返回数据
     */
    public static <T> CommonResult<T> success(String msg, T data) {
        return new CommonResult<>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 成功返回结果
     *
     * @param data 返回数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), data);
    }

    /**
     * 操作失败返回结果
     *
     * @param responseCode 通用返回信息
     */
    public static <T> CommonResult<T> fail(IResponseCode responseCode) {
        return new CommonResult<>(responseCode.getCode(), responseCode.getMsg(), null);
    }

    /**
     * 操作失败返回结果
     *
     * @param code 自定义返回码
     * @param msg  自定义返回信息
     */
    public static <T> CommonResult<T> fail(Integer code, String msg) {
        return new CommonResult<>(code, msg, null);
    }
}
