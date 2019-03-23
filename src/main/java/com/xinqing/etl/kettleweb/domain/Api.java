package com.xinqing.etl.kettleweb.domain;

import java.io.Serializable;

/**
 * api返回格式
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class Api<T> implements Serializable {

    private Boolean success;

    private String message;

    private T data;

    private Api() {
    }

    private Api(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param <T>  类型
     * @return Api
     */
    public static <T> Api<T> ok(T data) {
        return new Api<>(true, null, data);
    }

    /**
     * 失败
     *
     * @param message 信息
     * @param data    数据
     * @param <T>     类型
     * @return Api
     */
    public static <T> Api<T> fail(String message, T data) {
        return new Api<>(false, message, data);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
