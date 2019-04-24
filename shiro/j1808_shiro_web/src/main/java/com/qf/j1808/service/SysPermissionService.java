package com.qf.j1808.service;

import com.qf.j1808.pojo.SysPermission;

import java.util.List;

public interface SysPermissionService {

    public List<SysPermission> queryPermissionByUsername(String username);

}
