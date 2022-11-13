package com.ysoztf.security.service.seurity.bean;

import org.springframework.security.core.GrantedAuthority;

public class CustomAuthority implements GrantedAuthority {
    private String authority;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
