package com.ysoztf.security.interceptor;

import com.ysoztf.security.controller.response.CommonErrorCode;
import com.ysoztf.security.controller.response.CommonException;
import com.ysoztf.security.controller.response.CommonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(annotations = Controller.class)
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public CommonResponse handleException(Exception exception) {
        // TODO: 2022/11/14 日志模块引入后，记录错误信息
        if (exception instanceof CommonException) {
            return CommonResponse.CommonResponseBuilder
                    .getCommonResponseBuilder()
                    .withCommonException((CommonException) exception)
                    .build();
        }
        Throwable cause = exception.getCause();
        if (cause instanceof CommonException) {
            return CommonResponse.CommonResponseBuilder
                    .getCommonResponseBuilder()
                    .withCommonException((CommonException) cause)
                    .build();
        }
        return CommonResponse.CommonResponseBuilder
                .getCommonResponseBuilder()
                .withCommonErrorCode(CommonErrorCode.SYSTEM_ERROR)
                .build();
    }

}
