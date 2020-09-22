package com.mt.fpb.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.github.pagehelper.PageHelper;
import com.mt.fpb.common.util.GetToken;
import com.mt.fpb.common.util.RedisUtil;
import com.mt.fpb.mapper.RoleUserMapper;
import com.mt.fpb.mapper.SysUserMapper;
import com.mt.fpb.model.RoleUser;
import com.mt.fpb.model.SysUser;
import com.mt.fpb.model.dto.BaseQueryParams;
import com.mt.fpb.model.vo.CommonPage;
import com.mt.fpb.model.vo.CommonResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/SysUser")
public class SysUserController {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private RedisUtil redis;
    @Resource
    private RoleUserMapper roleUserMapper;

    /**
     * 登录
     *
     * @param sysUser
     * @return
     */
    @PostMapping("login")
    public CommonResult login(@RequestBody SysUser sysUser) {
      String name =   sysUser.getLoginName();
      String pass = sysUser.getPassword();
        System.err.println("调用  login  接口-----------------参数" + sysUser.getLoginName() + "-----------" + sysUser.getPassword());
        if (StrUtil.isBlank(sysUser.getLoginName()) || StrUtil.isBlank(sysUser.getPassword())) {
            return CommonResult.fail(-1, "用户名或密码错误");
        }
        SysUser user = sysUserMapper.selectOne(sysUser);
        String token;
        if (!StringUtils.isEmpty(user)) {
            token = GetToken.getToken(user);
            redis.set(token, token, 1800);
        } else {
            return CommonResult.fail(-1, "登录失败用户不存在");
        }
        return CommonResult.success(token);
    }


    /**
     * 获取角色信息及权限
     *
     * @param request
     * @return
     */
    @GetMapping("getUserInfo")
    public CommonResult getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        if (token == null) {
            return CommonResult.fail(-1, "token失效");
        }
        String userId = JWT.decode(token).getAudience().get(0);
        if (userId != null) {
            Map<String, String> user = sysUserMapper.getUserInfo(userId);
            List<Map<String, String>> menu = sysUserMapper.getUserMenu(userId);
            JSONObject json = new JSONObject();
            json.put("user", user);
            json.put("menu", menu);
            return CommonResult.success(json);

        }
        return CommonResult.fail(-1, "token过期请重新登录");
    }

    @PostMapping("logOut")
    public CommonResult logOut(HttpServletRequest request) {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        if (token == null) {
            return CommonResult.fail(-1, "token失效");
        }
        redis.del(token);
        return CommonResult.success(1);
    }

    /**
     * 添加账号
     *
     * @param sysUser
     * @return
     */
    @PostMapping("add")
    public CommonResult add(@RequestBody SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getLoginName()) || StringUtils.isEmpty(sysUser.getPassword())) {
            return CommonResult.fail(-1, "请输入用户名或密码");
        }
        if (StringUtils.isEmpty(sysUser.getRoleId())) {
            return CommonResult.fail(-1, "角色id不能为空");
        }
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginName", sysUser.getLoginName());
        List<SysUser> list = sysUserMapper.selectByExample(example);
        if (list.size() > 0) {
            return CommonResult.fail(-1, "账号已存在");
        }
        sysUser.setAddTime(new Date());
        sysUserMapper.insertUseGeneratedKeys(sysUser);
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(sysUser.getId());
        roleUser.setRoleId(sysUser.getRoleId());
        roleUserMapper.insert(roleUser);
        return CommonResult.success(1);
    }

    /**
     * 列表
     *
     * @param queryParams
     * @return
     */
    @GetMapping("list")
    public CommonResult list(BaseQueryParams queryParams) {
        PageHelper.startPage(queryParams.getPage(), queryParams.getPageSize());
        return CommonResult.success(CommonPage.restPage(sysUserMapper.list(queryParams.getName())));
    }

    /**
     * 改
     *
     * @param sysUser
     * @return
     */
    @PostMapping("update")
    public CommonResult update(@RequestBody SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getId())) {
            return CommonResult.fail(-1, "ID不能为空");
        }
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", sysUser.getId());
        sysUserMapper.updateByExample(sysUser, example);
        Example examples = new Example(RoleUser.class);
        Example.Criteria criterias = examples.createCriteria();
        criterias.andEqualTo("userId", sysUser.getId());
        RoleUser roleUser = new RoleUser();
        roleUser.setRoleId(sysUser.getRoleId());
        roleUser.setUserId(sysUser.getId());
        roleUserMapper.updateByExample(roleUser, examples);
        return CommonResult.success(1);
    }

    /**
     * 根据ID获取
     *
     * @param sysUser
     * @return
     */
    @GetMapping("getById")
    public CommonResult getById(SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getId())) {
            return CommonResult.fail(-1, "ID不能为空");
        }
        sysUser = sysUserMapper.selectOne(sysUser);
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(sysUser.getId());
        roleUser = roleUserMapper.selectOne(roleUser);
        sysUser.setRoleId(roleUser.getRoleId());
        return CommonResult.success(sysUser);
    }

    /**
     * 删除
     *
     * @param sysUser
     * @return
     */
    @PostMapping("delete")
    public CommonResult delete(@RequestBody SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getId())) {
            return CommonResult.fail(-1, "ID不能为空");
        }
        sysUserMapper.delete(sysUser);
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(sysUser.getId());
        roleUserMapper.delete(roleUser);
        return CommonResult.success(1);
    }

}