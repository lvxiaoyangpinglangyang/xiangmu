package com.qf.j1808.mapper;

import com.qf.j1808.pojo.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionMapper {

   public List<SysPermission> findPermissionByName(String username);
}
