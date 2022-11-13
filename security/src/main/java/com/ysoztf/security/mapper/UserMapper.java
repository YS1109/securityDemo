package com.ysoztf.security.mapper;

import com.ysoztf.security.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    SysUser getUserByUserName(String userName);
}
