package com.ysoztf.security.service.user;

import com.ysoztf.security.controller.response.CommonException;
import com.ysoztf.security.entity.SysUser;

public interface SysUserService {
    int addSysUser(SysUser sysUser) throws CommonException;
}
