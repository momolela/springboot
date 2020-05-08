package com.momolela.common;

/**
 * 返回状态码规范
 */
public enum ResponseStatus {

    SUCCESS("200", "success"),
    SERVER_EXCEPTION("500", "系统内部异常");

    private String code;
    private String message;

    ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
