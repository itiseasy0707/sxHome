package com.mt.fpb.controller;

import com.github.pagehelper.PageHelper;
import com.mt.fpb.mapper.RelationMapper;
import com.mt.fpb.model.Relation;
import com.mt.fpb.model.Relation;
import com.mt.fpb.model.dto.BaseQueryParams;
import com.mt.fpb.model.vo.CommonPage;
import com.mt.fpb.model.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 亲属表(Relation)表控制层
 *
 * @author makejava
 * @since 2020-09-22 15:42:17
 */
@RestController
@RequestMapping("/relationController")
public class RelationController {
    @Autowired
    private RelationMapper relationMapper;


    /**
     * 根据老人id查询其亲属列表
     * @param olId 老人id
     * @return
     */
    @GetMapping("/listByOlId/{olId}")
    public CommonResult listByOlId(@PathVariable("olId")Integer olId){
        Example example = new Example(Relation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oldmanId", olId);
        List<Relation> list = relationMapper.selectByExample(example);
        return CommonResult.success(list);
    }

    /**
     * 查询一个亲属详情
     * @param relation
     * @return
     */
    @GetMapping("/getById")
    public CommonResult getById(Relation relation){
        if (StringUtils.isEmpty(relation.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        Relation ca = relationMapper.selectOne(relation);
        return CommonResult.success(ca);
    }

    /**
     * 修改亲属信息
     * @param relation
     * @return
     */
    @PostMapping("/update")
    public CommonResult update(@RequestBody Relation relation){
        if (StringUtils.isEmpty(relation.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        Example example = new Example(Relation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", relation.getId());
        relationMapper.updateByExample(relation, example);
        return CommonResult.success(1);
    }

    /**
     * 添加亲属信息
     * @param relation 待添加亲属信息
     * @return
     */
    @PostMapping("/add")
    public CommonResult add(@RequestBody Relation relation){
        relationMapper.insert(relation);
        return CommonResult.success(1);
    }

    /**
     * 删除亲属信息
     * @param relation
     * @return
     */
    @DeleteMapping("/delete")
    public CommonResult delete(Relation relation) {
        if (StringUtils.isEmpty(relation.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        relationMapper.delete(relation);
        return CommonResult.success(1);
    }

}