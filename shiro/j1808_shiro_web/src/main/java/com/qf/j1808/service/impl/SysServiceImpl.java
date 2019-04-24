package com.qf.j1808.service.impl;

import com.qf.j1808.mapper.SysUserMapper;
import com.qf.j1808.pojo.SysUser;
import com.qf.j1808.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysServiceImpl implements SysUserService {
   @Autowired
   private SysUserMapper sysUserMapper;

    @Override
    public SysUser queryUserByUsername(String username) {


        return sysUserMapper.findUSerByUsername(username);
    }
}
