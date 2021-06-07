package com.hry.project.dictation.dto.page;


/**
 * @Author: huangrongyou@yixin.im on 2017/9/22.
 */
public class CommonEntity<T> extends CommonRsp {
    public CommonEntity() {
    }

    public CommonEntity(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public CommonEntity(T data) {
        this.data = data;
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * @param code
     * @param message
     */
    public void setErrorEntity(Integer code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public static <T> CommonEntity<T> create(T info) {
        return new CommonEntity<>(info);
    }

    public static <T> CommonEntity<T> create(Integer code, String message) {
        return new CommonEntity<>(code, message);
    }
}
