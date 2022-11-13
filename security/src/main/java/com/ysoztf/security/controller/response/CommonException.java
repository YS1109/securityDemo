package com.ysoztf.security.controller.response;

public class CommonException extends Exception{
    private CommonErrorCode commonErrorCode;


    public CommonErrorCode getCommonErrorCode() {
        return commonErrorCode;
    }

    public void setCommonErrorCode(CommonErrorCode commonErrorCode) {
        this.commonErrorCode = commonErrorCode;
    }

    public CommonException() {
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(CommonErrorCode commonErrorCode) {
        super(commonErrorCode.getErrorMessage());
        this.commonErrorCode = commonErrorCode;
    }

    public CommonException(Throwable cause, CommonErrorCode commonErrorCode) {
        super(commonErrorCode.getErrorMessage(), cause);
        this.commonErrorCode = commonErrorCode;
    }
}
