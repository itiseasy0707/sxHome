package com.mt.fpb.controller;

import com.github.pagehelper.PageHelper;
import com.mt.fpb.common.util.HintUtil;
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
 * 老人表(Oldman)表控制层
 *
 * @author makejava
 * @since 2020-09-22 10:59:08
 */
@RestController
@RequestMapping("/sxHome")
public class SxHomeController {

    @Autowired
    private SxHomeMapper sxHomeMapper;

    /**
     * 颐养之家列表
     *
     * @param queryParams 分页参数
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(BaseQueryParams queryParams) {

        PageHelper.startPage(queryParams.getPage(), queryParams.getPageSize());

        Example example = new Example(SxHome.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", queryParams.getUserId()); // 根据userId显示对应的信息
        List<SxHome> list = sxHomeMapper.selectByExample(example);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 详情
     *
     * @param sxHome
     * @return
     */
    @GetMapping("/getById")
    public CommonResult getById(SxHome sxHome) {
        if (StringUtils.isEmpty(sxHome.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        SxHome sxHome1 = sxHomeMapper.selectOne(sxHome);
        return CommonResult.success(sxHome1);
    }

    /**
     * 修改老人信息
     *
     * @param sxHome
     * @return
     */
    @PostMapping("/update")
    public CommonResult update(@RequestBody SxHome sxHome) {
        if (StringUtils.isEmpty(sxHome.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        Example example = new Example(SxHome.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", sxHome.getId());
        int cnt = sxHomeMapper.updateByExampleSelective(sxHome, example);
        return HintUtil.update(cnt);
    }

    /**
     * 添加颐养之家信息
     *
     * @param sxHome
     * @return
     */
    @PostMapping("/add")
    public CommonResult add(@RequestBody SxHome sxHome) {
        int cnt = sxHomeMapper.insert(sxHome);
        return HintUtil.insert(cnt);
    }

    /**
     * 删除颐养之家信息
     *
     * @param sxHome
     * @return
     */
    @DeleteMapping("/delete")
    public CommonResult delete(SxHome sxHome) {
        if (StringUtils.isEmpty(sxHome.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        int cnt = sxHomeMapper.delete(sxHome);
        return HintUtil.delete(cnt);
    }

}