package com.mt.fpb.controller;

import com.mt.fpb.mapper.SysMenuMapper;
import com.mt.fpb.model.SysMenu;
import com.mt.fpb.model.vo.CommonResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/SysMenu")
public class SysMenuController {
    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 添加
     *
     * @param sysMenu
     * @return
     */
    @PostMapping("add")
    public CommonResult add(@RequestBody SysMenu sysMenu) {
        if (StringUtils.isEmpty(sysMenu.getMenuName())) {
            return CommonResult.fail(-1, "菜单名称不能为空");
        }
        sysMenu.setAddTime(new Date());
        sysMenuMapper.insert(sysMenu);
        return CommonResult.success(1);
    }

    /**
     * 列表
     *
     * @param sysMenu
     * @return
     */
    @GetMapping("list")
    public CommonResult list(SysMenu sysMenu) {
        return CommonResult.success(sysMenuMapper.selectAll());
    }

    /**
     * 修改
     *
     * @param sysMenu
     * @return
     */
    @PostMapping("update")
    public CommonResult update(@RequestBody SysMenu sysMenu) {
        if (StringUtils.isEmpty(sysMenu.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        Example example = new Example(SysMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", sysMenu.getId());
        sysMenuMapper.updateByExample(sysMenu, example);
        return CommonResult.success(1);
    }

    /**
     * 根据ID获取
     *
     * @param sysMenu
     * @return
     */
    @GetMapping("getById")
    public CommonResult getById(SysMenu sysMenu) {
        if (StringUtils.isEmpty(sysMenu.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        return CommonResult.success(sysMenuMapper.selectOne(sysMenu));
    }

    /**
     * 删除
     *
     * @param sysMenu
     * @return
     */
    @PostMapping("delete")
    public CommonResult delete(@RequestBody SysMenu sysMenu) {
        if (StringUtils.isEmpty(sysMenu.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        sysMenuMapper.delete(sysMenu);
        return CommonResult.success(1);
    }


}
