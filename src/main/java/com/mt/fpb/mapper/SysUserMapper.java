package com.mt.fpb.mapper;

import com.mt.fpb.common.config.MyMapper;
import com.mt.fpb.model.SysUser;
import com.mt.fpb.model.dto.BaseQueryParams;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends MyMapper<SysUser> {

    List<Map<String, String>> list(String loginName);

    Map<String, String> getUserInfo(String userId);

    List<Map<String,String>> getUserMenu(String userId);
}