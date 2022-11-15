package com.ysoztf.security.service.user;

import com.ysoztf.security.controller.response.CommonErrorCode;
import com.ysoztf.security.controller.response.CommonException;
import com.ysoztf.security.entity.SysUser;
import com.ysoztf.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int addSysUser(SysUser sysUser) throws CommonException {
        SysUser localSysUser = userMapper.getUserByUserName(sysUser.getUserName());
        if (localSysUser != null) {
            throw new CommonException(CommonErrorCode.USERNAME_HAS_EXISTED);
        }
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        return userMapper.addSysUser(sysUser);
    }
}
