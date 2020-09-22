package com.mt.fpb.controller;

import com.github.pagehelper.PageHelper;
import com.mt.fpb.mapper.RoleMenuMapper;
import com.mt.fpb.mapper.SysMenuMapper;
import com.mt.fpb.mapper.SysRoleMapper;
import com.mt.fpb.model.RoleMenu;
import com.mt.fpb.model.SysMenu;
import com.mt.fpb.model.SysRole;
import com.mt.fpb.model.dto.BaseQueryParams;
import com.mt.fpb.model.vo.CommonPage;
import com.mt.fpb.model.vo.CommonResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/SysRole")
public class SysRoleController {
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 添加角色信息
     *
     * @param sysRole
     * @return
     */
    @PostMapping("add")
    public CommonResult add(@RequestBody SysRole sysRole) {
        if (StringUtils.isEmpty(sysRole.getRoleName())) {
            return CommonResult.fail(-1, "角色名称不能为空");
        }
        sysRole.setAddTime(new Date());
        return CommonResult.success(sysRoleMapper.insert(sysRole));
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
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(queryParams.getName())) {
            criteria.andEqualTo("roleName", queryParams.getName());
        }
        List<SysRole> list = sysRoleMapper.selectByExample(example);
        List<SysMenu> sysMenuList = roleMenuMapper.roleMenuList();
        for (SysRole sysRole : list) {
            List<SysMenu> model = new ArrayList<>();
            for (SysMenu sysMenu : sysMenuList) {
                if (sysRole.getId() == sysMenu.getRoleId()) {
                    model.add(sysMenu);
                }
            }
            sysRole.setRules(model);
        }
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 改
     *
     * @param sysRole
     * @return
     */
    @PostMapping("update")
    public CommonResult update(@RequestBody SysRole sysRole) {
        if (StringUtils.isEmpty(sysRole)) {
            return CommonResult.fail(-1, "id不能为空");
        }
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", sysRole.getId());
        sysRoleMapper.updateByExample(sysRole, example);
        return CommonResult.success(1);
    }

    /**
     * 删除
     *
     * @param sysRole
     * @return
     */
    @PostMapping("delete")
    public CommonResult delete(@RequestBody SysRole sysRole) {
        if (StringUtils.isEmpty(sysRole)) {
            return CommonResult.fail(-1, "id不能为空");
        }
        sysRoleMapper.delete(sysRole);
        return CommonResult.success(1);
    }

    /**
     * 根据id获取
     *
     * @param sysRole
     * @return
     */
    @GetMapping("getById")
    public CommonResult getById(SysRole sysRole) {
        if (StringUtils.isEmpty(sysRole)) {
            return CommonResult.fail(-1, "id不能为空");
        }
        return CommonResult.success(sysRoleMapper.selectOne(sysRole));
    }

    /**
     * 为角色添加权限
     *
     * @param roleMenu
     * @return
     */
    @PostMapping("addRoleMenu")
    public CommonResult addRoleMenu(@RequestBody RoleMenu roleMenu) {
        if (StringUtils.isEmpty(roleMenu.getRoleId()) || roleMenu.getMenuIds().size() < 1) {
            return CommonResult.fail(-1, "角色ID或菜单ID不能为空");
        }
        for (Integer ids : roleMenu.getMenuIds()) {
            roleMenu.setMenuId(ids);
            roleMenuMapper.insert(roleMenu);
        }
        return CommonResult.success(1);
    }

    /**
     * 添加权限
     *
     * @param roleMenu
     * @return
     */
    @PostMapping("updateRoleMenu")
    public CommonResult updateRoleMenu(@RequestBody RoleMenu roleMenu) {
        if (StringUtils.isEmpty(roleMenu.getRoleId()) || roleMenu.getMenuIds().size() < 1) {
            return CommonResult.fail(-1, "角色id或菜单id不能为空");
        }
        Example example = new Example(RoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleMenu.getRoleId());
        List<RoleMenu> list = roleMenuMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            roleMenuMapper.deleteByExample(example);
            for (Integer menuId : roleMenu.getMenuIds()) {
                RoleMenu rm = new RoleMenu();
                rm.setMenuId(menuId);
                rm.setRoleId(roleMenu.getRoleId());
                roleMenuMapper.insert(rm);
            }
        } else {
            for (Integer menuId : roleMenu.getMenuIds()) {
                RoleMenu rm = new RoleMenu();
                rm.setMenuId(menuId);
                rm.setRoleId(roleMenu.getRoleId());
                roleMenuMapper.insert(rm);
            }
        }
        return CommonResult.success(1);
    }


}
