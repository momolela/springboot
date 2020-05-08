package com.momolela.common;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回的对象
 */
@Data
public class ResponseEntity<T> implements Serializable {

    private Meta meta;
    private T data = null;

    /**
     * 默认请求成功 200
     *
     * @return
     */
    public ResponseEntity success() {
        this.setMeta(new Meta(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMessage()));
        return this;
    }

    /**
     * 默认请求失败 服务端异常 500
     *
     * @return
     */
    public ResponseEntity failure() {
        this.setMeta(new Meta(ResponseStatus.SERVER_EXCEPTION.getCode(), ResponseStatus.SERVER_EXCEPTION.getMessage()));
        return this;
    }

    /**
     * 自定义失败的信息和错误码
     *
     * @param ResponseStatus
     * @return
     */
    public ResponseEntity failure(ResponseStatus ResponseStatus) {
        this.setMeta(new Meta(ResponseStatus.getCode(), ResponseStatus.getMessage()));
        return this;
    }

    /**
     * 自定义失败的信息和错误码
     *
     * @param code
     * @param message
     * @return
     */
    public ResponseEntity failure(String code, String message) {
        this.setMeta(new Meta(code, message));
        return this;
    }

    /**
     * 内部类，把返回数据和返回状态码信息隔离开
     */
    @Data
    private static class Meta implements Serializable {

        private String statusCode;

        private String message;

        Meta(String statusCode, String message) {
            this.statusCode = statusCode;
            this.message = message;
        }
    }

    /**
     * 将对象转成json字符串
     *
     * @return
     */
    public String toJson() {
        return JSON.toJSONString(this);
    }
}

