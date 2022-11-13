package com.ysoztf.security.handler.security;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.ysoztf.security.controller.response.CommonResponse;
import com.ysoztf.security.service.seurity.bean.SystemUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private Gson gson;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> result = Maps.newHashMap();
        SystemUserDetail userDetail = (SystemUserDetail) authentication.getPrincipal();
        String token = String.valueOf(UUID.randomUUID());
        result.put("token", token);
        redisTemplate.opsForValue().set(token, userDetail);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(200);
        CommonResponse commonResponse = CommonResponse.CommonResponseBuilder.
                getCommonResponseBuilder().
                withResult(result).
                build();
        response.getWriter().write(gson.toJson(commonResponse));
    }
}
