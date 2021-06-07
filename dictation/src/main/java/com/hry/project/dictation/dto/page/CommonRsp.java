package com.hry.project.dictation.dto.page;

/**
 * 通过的返回内容
 *
 * @Author: huangrongyou@yixin.im on 2017/9/21.
 */
public class CommonRsp {
    /**
     * 状态码
     */
    private Integer code = 200;
    /**
     * 提示消息或者错误消息
     */
    private String message = "";

    static public CommonRsp ok = new CommonRsp(200, "OK");

    public CommonRsp() {
    }

    public CommonRsp(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CommonRsp getOkCommonRsp() {
        return ok;
    }

    public void setError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonRsp [code=" + code + ", message=" + message + "]";
    }
}
