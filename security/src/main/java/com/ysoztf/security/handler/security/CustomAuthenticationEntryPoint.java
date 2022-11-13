package com.ysoztf.security.handler.security;

import com.google.gson.Gson;
import com.ysoztf.security.controller.response.CommonErrorCode;
import com.ysoztf.security.controller.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    private Gson gson;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(200);
        CommonResponse commonResponse = CommonResponse.CommonResponseBuilder.
                getCommonResponseBuilder().
                withCommonErrorCode(CommonErrorCode.INVALID_TOKEN).
                build();
        response.getWriter().write(gson.toJson(commonResponse));
    }
}
