package com.mt.fpb.mapper;

import com.mt.fpb.common.config.MyMapper;
import com.mt.fpb.model.RoleMenu;
import com.mt.fpb.model.SysMenu;

import java.util.List;
import java.util.Map;


public interface RoleMenuMapper extends MyMapper<RoleMenu> {

    List<SysMenu> roleMenuList();
}