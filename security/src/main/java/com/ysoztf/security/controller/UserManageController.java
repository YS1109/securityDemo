package com.ysoztf.security.controller;

import com.ysoztf.security.controller.response.CommonException;
import com.ysoztf.security.controller.response.CommonResponse;
import com.ysoztf.security.entity.SysUser;
import com.ysoztf.security.service.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserManageController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/createUser")
    public CommonResponse createUser(@RequestBody SysUser sysUser) throws CommonException {
        sysUserService.addSysUser(sysUser);
        return CommonResponse.CommonResponseBuilder
                .getCommonResponseBuilder()
                .build();
    }
}
