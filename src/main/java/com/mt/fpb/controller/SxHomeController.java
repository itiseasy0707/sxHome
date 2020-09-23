package com.mt.fpb.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.mt.fpb.mapper.OldmanMapper;
import com.mt.fpb.mapper.SxHomeMapper;
import com.mt.fpb.model.Oldman;
import com.mt.fpb.model.SxHome;
import com.mt.fpb.model.dto.BaseQueryParams;
import com.mt.fpb.model.vo.CommonPage;
import com.mt.fpb.model.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 水西颐养之家(SxHome)表控制层
 *
 * @author makejava
 * @since 2020-09-23 14:07:50
 */
@RestController
@RequestMapping("/sxHomeController")
public class SxHomeController {
    /**
     * 服务对象
     */
    @Autowired
    private SxHomeMapper sxHomeMapper;

    @Autowired
    private OldmanMapper oldmanMapper;

    /**
     * 查询所有 水西颐养之家
     * @param queryParams
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(BaseQueryParams queryParams){
        PageHelper.startPage(queryParams.getPage(),queryParams.getPageSize());
        List<SxHome> list = sxHomeMapper.selectAll();
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 根据主键id查询水西颐养之家
     * @param sxHome 主键实体
     * @return
     */
    @GetMapping("/getById")
    public CommonResult getById(SxHome sxHome){
        if (StringUtils.isEmpty(sxHome.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        SxHome bt = sxHomeMapper.selectOne(sxHome);
        //根据userId查询老人信息
        Example example = new Example(Oldman.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", bt.getUserId());
        List<Oldman> list = oldmanMapper.selectByExample(example);
        JSONObject json = new JSONObject();
        json.put("sxHome", bt);//单个颐养之家信息
        json.put("list", list);//颐养之家下老人信息
        return CommonResult.success(json);
    }

    /**
     * 修改水西颐养之家信息
     * @param sxHome 颐养之家对象
     * @return 修改结果
     */
    @PostMapping("/update")
    public CommonResult update(@RequestBody SxHome sxHome){
        if (StringUtils.isEmpty(sxHome.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        Example example = new Example(SxHome.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", sxHome.getId());
        sxHomeMapper.updateByExample(sxHome, example);
        return CommonResult.success(1);
    }

    /**
     * 新增颐养之家信息
     * @param sxHome 颐养之家对象
     * @return 添加结果
     */
    @PostMapping("/add")
    public CommonResult add(@RequestBody SxHome sxHome){
        sxHomeMapper.insert(sxHome);
        return CommonResult.success(1);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return CommonResult.fail(-1, "id不能为空");
        }
        sxHomeMapper.deleteByPrimaryKey(id);
        return CommonResult.success(1);
    }

}