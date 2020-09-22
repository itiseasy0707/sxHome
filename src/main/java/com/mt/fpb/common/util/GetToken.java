package com.mt.fpb.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mt.fpb.model.SysUser;


public class GetToken {

    public static String getToken(SysUser sysUser) {
        String token = "";
        token = JWT.create().withAudience(sysUser.getId().toString()).sign(Algorithm.HMAC256(sysUser.getPassword()));
        return token;
    }
}
