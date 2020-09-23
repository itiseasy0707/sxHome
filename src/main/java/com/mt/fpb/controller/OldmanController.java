package com.mt.fpb.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.mt.fpb.common.util.HintUtil;
import com.mt.fpb.mapper.OldHealthMapper;
import com.mt.fpb.mapper.OldRelationMapper;
import com.mt.fpb.mapper.OldmanMapper;
import com.mt.fpb.model.OldHealth;
import com.mt.fpb.model.OldRelation;
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
@RequestMapping("/oldManController")
public class OldmanController {

    @Autowired
    private OldmanMapper oldmanMapper;

    @Autowired
    private OldHealthMapper oldHealthMapper;

    @Autowired
    private OldRelationMapper oldRelationMapper;

    /**
     * 老人列表
     *
     * @param queryParams 分页参数
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(BaseQueryParams queryParams) {

        PageHelper.startPage(queryParams.getPage(), queryParams.getPageSize());

        Example example = new Example(Oldman.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", queryParams.getUserId()); // 根据userId显示对应的信息
        List<Oldman> list = oldmanMapper.selectByExample(example);

        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 详情
     *
     * @param oldman
     * @return
     */
    @GetMapping("/getById")
    public CommonResult getById(Oldman oldman) {
        if (StringUtils.isEmpty(oldman.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        //1、 查询出老人的基本数据
        Oldman oldman1 = oldmanMapper.selectOne(oldman);
        //2、 查询出老人的健康数据(导入操作，这里不需要)
//        OldHealth oldHealth = new OldHealth();
//        oldHealth.setOldId(oldman1.getId());
//        OldHealth oldHealth1 = oldHealthMapper.selectOne(oldHealth);
        //3、 查询出老人的家属信息
        OldRelation oldRelation = new OldRelation();
        oldRelation.setOldmanId(oldman1.getId());
        Example example = new Example(OldRelation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oldmanId", oldman1.getId());
        List<OldRelation> list = oldRelationMapper.selectByExample(example);

        JSONObject json = new JSONObject();
//        json.put("oldHealth1", oldHealth1);
        json.put("oldman1", oldman1);
        json.put("list", list);
        return CommonResult.success(json);
    }

    /**
     * 修改老人信息
     *
     * @param oldman
     * @return
     */
    @PostMapping("/update")
    public CommonResult update(@RequestBody Oldman oldman) {
        if (StringUtils.isEmpty(oldman.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        Example example = new Example(Oldman.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", oldman.getId());
        int cnt = oldmanMapper.updateByExample(oldman, example);
        return CommonResult.success(cnt);
    }

    /**
     * 修改老人的健康信息
     *
     * @param oldHealth
     * @return
     */
//    @PostMapping("/modifyOldHealth")
//    public CommonResult modifyOldHealth(@RequestBody OldHealth oldHealth) {
//        if (!StringUtils.isEmpty(oldHealth.getId())) { // 不为空  做修改操作
//            Example example = new Example(OldHealth.class);
//            Example.Criteria criteria = example.createCriteria();
//            criteria.andEqualTo("id", oldHealth.getId());
//            int cnt = oldHealthMapper.updateByExample(oldHealth, example);
//            return HintUtil.update(cnt);
//        } else { // 为空 新增
//            int cnt = oldHealthMapper.insert(oldHealth);
//            return HintUtil.insert(cnt);
//
//        }
//
//    }

    /**
     * 修改老人的家属信息
     *
     * @param oldRelation
     * @return
     */
    @PostMapping("/modifyOldHealth")
    public CommonResult modifyOldRelation(@RequestBody OldRelation oldRelation) {
        if (!StringUtils.isEmpty(oldRelation.getId())) { // 不为空  做修改操作
            Example example = new Example(OldRelation.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id", oldRelation.getId());
            int cnt = oldRelationMapper.updateByExample(oldRelation, example);
            return HintUtil.update(cnt);
        } else { // 为空 新增
            int cnt = oldRelationMapper.insert(oldRelation);
            return HintUtil.insert(cnt);

        }

    }


    /**
     * 添加老人的基本信息
     *
     * @param oldman 待添加老人信息
     * @return
     */
    @PostMapping("/add")
    public CommonResult add(@RequestBody Oldman oldman) {
        int cnt = oldmanMapper.insert(oldman);
        return HintUtil.insert(cnt);
    }

    /**
     * 删除老人信息
     *
     * @param oldman
     * @return
     */
    @DeleteMapping("/delete")
    public CommonResult delete(Oldman oldman) {
        if (StringUtils.isEmpty(oldman.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        int cnt = oldmanMapper.delete(oldman);
        return HintUtil.delete(cnt);
    }

}