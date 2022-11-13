package com.ysoztf.security.service.seurity.bean;

import com.ysoztf.security.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class SystemUserDetail implements UserDetails, Serializable {
    private static final long serialVersionUID = -3451657885556643443L;
    private SysUser sysUser;

    public SysUser getSysUser() {
        return sysUser;
    }
    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SystemUserDetail(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return sysUser.getExpireTime().getTime() > System.currentTimeMillis();
    }

    @Override
    public boolean isAccountNonLocked() {
        return sysUser.getStatus() != 2;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sysUser.getStatus() != 0;
    }
}
