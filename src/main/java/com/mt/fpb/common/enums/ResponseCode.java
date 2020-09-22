package com.mt.fpb.common.enums;

/**
 * 通用返回code和msg
 */
public enum ResponseCode implements IResponseCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    UNAUTH(403, "没有相关权限"),
    UNLOGIN(401, "未登录或登陆过期");

    private  Integer code;
    private String msg;

    private ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
