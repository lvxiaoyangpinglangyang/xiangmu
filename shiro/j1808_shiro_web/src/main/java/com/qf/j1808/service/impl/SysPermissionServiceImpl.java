package com.qf.j1808.service.impl;

import com.qf.j1808.mapper.SysPermissionMapper;
import com.qf.j1808.mapper.SysUserMapper;
import com.qf.j1808.pojo.SysPermission;
import com.qf.j1808.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> queryPermissionByUsername(String username) {

        return sysPermissionMapper.findPermissionByName(username);
    }
}
