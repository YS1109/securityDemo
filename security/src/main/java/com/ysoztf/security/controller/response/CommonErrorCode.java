package com.ysoztf.security.controller.response;

public enum CommonErrorCode {

    SYSTEM_ERROR(100001L, "系统错误"),
    LOGIN_FAILED(100002L, "用户名或密码错误"),
    INVALID_TOKEN(100003L, "无效的token");


    private final long errorCode;
    private final String errorMessage;

    CommonErrorCode(long errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
