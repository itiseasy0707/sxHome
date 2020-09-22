package com.mt.fpb.controller;

import com.github.pagehelper.PageHelper;
import com.mt.fpb.mapper.OldmanMapper;
import com.mt.fpb.model.Oldman;
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
@RequestMapping("/oldManController")
public class OldmanController {
    
    @Autowired
    private OldmanMapper oldmanMapper;

    /**
     * 老人列表
     * @param queryParams 分页参数
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(BaseQueryParams queryParams){
        
        PageHelper.startPage(queryParams.getPage(),queryParams.getPageSize());
        List<Oldman> list = oldmanMapper.selectAll();
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 查询一个老人
     * @param oldman
     * @return
     */
    @GetMapping("/getById")
    public CommonResult getById(Oldman oldman){
        if (StringUtils.isEmpty(oldman.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        Oldman ca = oldmanMapper.selectOne(oldman);
        return CommonResult.success(ca);
    }

    /**
     * 修改老人信息
     * @param oldman
     * @return
     */
    @PostMapping("/update")
    public CommonResult update(@RequestBody Oldman oldman){
        if (StringUtils.isEmpty(oldman.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        Example example = new Example(Oldman.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", oldman.getId());
        oldmanMapper.updateByExample(oldman, example);
        return CommonResult.success(1);
    }

    /**
     * 添加老人信息
     * @param oldman 待添加老人信息
     * @return
     */
    @PostMapping("/add")
    public CommonResult add(@RequestBody Oldman oldman){
        oldmanMapper.insert(oldman);
        return CommonResult.success(1);
    }

    /**
     * 删除老人信息
     * @param oldman
     * @return
     */
    @DeleteMapping("/delete")
    public CommonResult delete(Oldman oldman) {
        if (StringUtils.isEmpty(oldman.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        oldmanMapper.delete(oldman);
        return CommonResult.success(1);
    }

}