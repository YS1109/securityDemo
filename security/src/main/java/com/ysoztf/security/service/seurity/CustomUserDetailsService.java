package com.ysoztf.security.service.seurity;

import com.ysoztf.security.entity.SysUser;
import com.ysoztf.security.mapper.UserMapper;
import com.ysoztf.security.service.seurity.bean.SystemUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userMapper.getUserByUserName(username);
        return new SystemUserDetail(sysUser);
    }
}
